package com.ddimitroff.projects.dwallet.rest.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.CashFlowType;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.managers.CashFlowManager;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.rest.DWalletRestUtils;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashRecordRO;
import com.ddimitroff.projects.dwallet.rest.exception.DWalletResponseException;
import com.ddimitroff.projects.dwallet.rest.token.Token;
import com.ddimitroff.projects.dwallet.rest.token.TokenGenerator;
import com.ddimitroff.projects.dwallet.rest.token.TokenRO;
import com.ddimitroff.projects.dwallet.rest.token.TokenWatcher;
import com.ddimitroff.projects.dwallet.xml.exchange.DWalletExchangeRatesParser;

@Controller
public class CashRecordsRestService {

	private static final Logger logger = Logger.getLogger(CashRecordsRestService.class);

	@Autowired
	private UserManager userManager;

	@Autowired
	private CashBalanceManager cashBalanceManager;

	@Autowired
	private CashFlowManager cashFlowManager;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private TokenWatcher tokenWatcher;

	@Autowired
	private DWalletExchangeRatesParser exchangeRatesParser;

	@Autowired
	private ArrayList<String> apiKeys;

	// Content-Type: application/json needed
	// DWallet-API-Key: <api-key> needed
	@RequestMapping(method = RequestMethod.GET, value = "/cash/test")
	@ResponseBody
	public CashRecordRO getCashRecord() throws DWalletResponseException {
		User daoToLogin = userManager.getUserByEmail("mykob.11@gmail.com");
		Token token = tokenGenerator.generate(daoToLogin);
		if (null != token) {
			TokenRO tokenRO = tokenGenerator.convert(token);
			CashFlowRO flow1 = new CashFlowRO(1, 1, 2.55);
			CashFlowRO flow2 = new CashFlowRO(2, 2, 5.33);
			CashFlowRO flow3 = new CashFlowRO(1, 2, 9.11);
			ArrayList<CashFlowRO> cashFlows = new ArrayList<CashFlowRO>();
			cashFlows.add(flow1);
			cashFlows.add(flow2);
			cashFlows.add(flow3);

			CashRecordRO record = new CashRecordRO(tokenRO, cashFlows);

			return record;
		} else {
			logger.error("Unable to generate token for user " + daoToLogin.getEmail());
			throw new DWalletResponseException("Unable to generate token for user " + daoToLogin.getEmail());
		}
	}

	// Content-Type: application/json needed
	// DWallet-API-Key: <api-key> needed
	@RequestMapping(method = RequestMethod.POST, value = "/cash/balance")
	@ResponseBody
	public CashBalanceRO getCashBalance(
			@RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
			@RequestBody TokenRO tokenRO) throws DWalletResponseException {
		if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
			if (null != tokenRO) {
				Token token = tokenWatcher.getTokenById(tokenRO.getTokenId());
				if (null != token) {
					CashBalance cashBalanceEntity = cashBalanceManager.getCashBalanceByUser(token.getOwner());
					if (null != cashBalanceEntity) {
						CashBalanceRO output = cashBalanceManager.convert(cashBalanceEntity);
						return output;
					} else {
						logger.error("Unable to find cash balance for user " + token.getOwner());
						throw new DWalletResponseException("Unable to find cash balance for user " + token.getOwner());
					}
				} else {
					logger.error("Unable to find token with id " + tokenRO.getTokenId());
					throw new DWalletResponseException("Unable to find token with id " + tokenRO.getTokenId());
				}
			} else {
				logger.error("Wrong request body post of cash record");
				throw new DWalletResponseException("Wrong request body post of cash record");
			}
		} else {
			logger.error("Wrong 'd-wallet' API key");
			throw new DWalletResponseException("Wrong 'd-wallet' API key");
		}
	}

	// Content-Type: application/json needed
	// DWallet-API-Key: <api-key> needed
	@RequestMapping(method = RequestMethod.POST, value = "/cash/post")
	@ResponseStatus(value = HttpStatus.OK)
	public void postCashRecord(
			@RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
			@RequestBody CashRecordRO cashRecordRO) throws DWalletResponseException {
		if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
			if (null != cashRecordRO) {
				Token token = tokenWatcher.getTokenById(cashRecordRO.getToken().getTokenId());
				if (null != token) {
					long start = System.nanoTime();

					CashBalance cashBalanceEntity = cashBalanceManager.getCashBalanceByUser(token.getOwner());
					if (null == cashBalanceEntity) {
						if (token.getOwner().getStartupBalance() < 0) {
							cashBalanceEntity = new CashBalance(token.getOwner(), token.getOwner().getDefaultCurrency(), 0, token
									.getOwner().getStartupBalance());
						} else {
							cashBalanceEntity = new CashBalance(token.getOwner(), token.getOwner().getDefaultCurrency(), token
									.getOwner().getStartupBalance(), 0);
						}
					}

					// iterate over every cash flow from the record
					for (int i = 0; i < cashRecordRO.getCashFlows().size(); i++) {
						CashFlowRO current = cashRecordRO.getCashFlows().get(i);
						CashFlow currentEntity = cashFlowManager.convert(current, token.getOwner());

						manageCashFlowCurrencies(currentEntity);

						if (CashFlowType.PROFIT == currentEntity.getType()) {
							cashBalanceEntity.setDebit(cashBalanceEntity.getDebit() + currentEntity.getSum());
						} else {
							cashBalanceEntity.setCredit(cashBalanceEntity.getCredit() + currentEntity.getSum());
						}

						cashFlowManager.save(currentEntity);
					}

					cashBalanceManager.save(cashBalanceEntity);
					logger.info("Importing cash record from user " + token.getOwner() + " finished in "
							+ (System.nanoTime() - start) / 1000000 + " ms.");
				} else {
					logger.error("Unable to find token with id " + cashRecordRO.getToken().getTokenId());
					throw new DWalletResponseException("Unable to find token with id " + cashRecordRO.getToken().getTokenId());
				}
			} else {
				logger.error("Wrong request body post of cash record");
				throw new DWalletResponseException("Wrong request body post of cash record");
			}
		} else {
			logger.error("Wrong 'd-wallet' API key");
			throw new DWalletResponseException("Wrong 'd-wallet' API key");
		}
	}

	/*
	 * Checks every cash flow and transforms each currency to BGN. Round double
	 * sum value to second sign after dot
	 */
	private void manageCashFlowCurrencies(CashFlow currentDAO) {
		double factor = 0.0;
		double refactoredSum = currentDAO.getSum();

		switch (currentDAO.getOwner().getDefaultCurrency()) {
		case BGN:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(), CashFlowCurrencyType.BGN);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowCurrencyType.BGN);
			break;
		case USD:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(), CashFlowCurrencyType.USD);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowCurrencyType.USD);
			break;
		case EUR:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(), CashFlowCurrencyType.EUR);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowCurrencyType.EUR);
			break;
		}
	}

	private double currencyExchangeFactor(CashFlowCurrencyType from, CashFlowCurrencyType to) {
		double exchangeRateFrom = 1.0;
		if (CashFlowCurrencyType.BGN != from) {
			exchangeRateFrom = exchangeRatesParser.getExchangeRateByCurrency(from.name());
		}

		double exchangeRateTo = 1.0;
		if (CashFlowCurrencyType.BGN != to) {
			exchangeRateTo = exchangeRatesParser.getExchangeRateByCurrency(to.name());
		}

		return exchangeRateFrom / exchangeRateTo;
	}

}