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

import com.ddimitroff.projects.dwallet.db.cash.CashBalanceDAO;
import com.ddimitroff.projects.dwallet.db.cash.CashDAOManager;
import com.ddimitroff.projects.dwallet.db.cash.CashFlowDAO;
import com.ddimitroff.projects.dwallet.db.cash.CashFlowDAOCurrencyType;
import com.ddimitroff.projects.dwallet.db.cash.CashFlowDAOType;
import com.ddimitroff.projects.dwallet.db.user.UserDAO;
import com.ddimitroff.projects.dwallet.db.user.UserDAOManager;
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

/**
 * This is an example REST style MVC controller. It serves as an endpoint for
 * retrieving Product Objects.
 */
@Controller
public class CashRecordsRestService {

	private static final Logger logger = Logger
			.getLogger(CashRecordsRestService.class);

	@Autowired
	private UserDAOManager userManager;

	@Autowired
	private CashDAOManager cashManager;

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
		UserDAO daoToLogin = userManager.getUserByName("mykob.11@gmail.com");
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
			logger.error("Unable to generate token for user "
					+ daoToLogin.getEmail());
			throw new DWalletResponseException(
					"Unable to generate token for user "
							+ daoToLogin.getEmail());
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
					CashBalanceDAO cashBalanceDAO = cashManager
							.getCashBalanceByUser(token.getOwner());
					if (null != cashBalanceDAO) {
						CashBalanceRO output = cashManager
								.convert(cashBalanceDAO);
						return output;
					} else {
						logger.error("Unable to find cash balance for user "
								+ token.getOwner());
						throw new DWalletResponseException(
								"Unable to find cash balance for user "
										+ token.getOwner());
					}
				} else {
					logger.error("Unable to find token with id "
							+ tokenRO.getTokenId());
					throw new DWalletResponseException(
							"Unable to find token with id "
									+ tokenRO.getTokenId());
				}
			} else {
				logger.error("Wrong request body post of cash record");
				throw new DWalletResponseException(
						"Wrong request body post of cash record");
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
			@RequestBody CashRecordRO cashRecordRO)
			throws DWalletResponseException {
		if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
			if (null != cashRecordRO) {
				Token token = tokenWatcher.getTokenById(cashRecordRO.getToken()
						.getTokenId());
				if (null != token) {
					long start = System.nanoTime();

					CashBalanceDAO cashBalanceDAO = cashManager
							.getCashBalanceByUser(token.getOwner());
					if (null == cashBalanceDAO) {
						cashBalanceDAO = new CashBalanceDAO(token.getOwner(),
								token.getOwner().getDefaultCurrency(), 0, 0);
					}

					// iterate over every cash flow from the record
					for (int i = 0; i < cashRecordRO.getCashFlows().size(); i++) {
						CashFlowRO current = cashRecordRO.getCashFlows().get(i);
						CashFlowDAO currentDAO = cashManager.convert(current,
								token.getOwner());

						manageCashFlowCurrencies(currentDAO);

						if (CashFlowDAOType.PROFIT == currentDAO.getType()) {
							cashBalanceDAO.setDebit(cashBalanceDAO.getDebit()
									+ currentDAO.getSum());
						} else {
							cashBalanceDAO.setCredit(cashBalanceDAO.getCredit()
									+ currentDAO.getSum());
						}

						cashManager.saveCashFlow(currentDAO);
					}
					
					cashManager.saveCashBalance(cashBalanceDAO);
					logger.info("Importing cash record from user "
							+ token.getOwner() + " finished in "
							+ (System.nanoTime() - start) / 1000000 + " ms.");
				} else {
					logger.error("Unable to find token with id "
							+ cashRecordRO.getToken().getTokenId());
					throw new DWalletResponseException(
							"Unable to find token with id "
									+ cashRecordRO.getToken().getTokenId());
				}
			} else {
				logger.error("Wrong request body post of cash record");
				throw new DWalletResponseException(
						"Wrong request body post of cash record");
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
	private void manageCashFlowCurrencies(CashFlowDAO currentDAO) {
		double factor = 0.0;
		double refactoredSum = currentDAO.getSum();

		switch (currentDAO.getOwner().getDefaultCurrency()) {
		case BGN:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(),
					CashFlowDAOCurrencyType.BGN);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowDAOCurrencyType.BGN);
			break;
		case USD:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(),
					CashFlowDAOCurrencyType.USD);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowDAOCurrencyType.USD);
			break;
		case EUR:
			factor = currencyExchangeFactor(currentDAO.getCurrencyType(),
					CashFlowDAOCurrencyType.EUR);
			refactoredSum *= factor;
			currentDAO.setSum(DWalletRestUtils.roundTwoDecimals(refactoredSum));
			currentDAO.setCurrencyType(CashFlowDAOCurrencyType.EUR);
			break;
		}
	}

	private double currencyExchangeFactor(CashFlowDAOCurrencyType from,
			CashFlowDAOCurrencyType to) {
		double exchangeRateFrom = 1.0;
		if (CashFlowDAOCurrencyType.BGN != from) {
			exchangeRateFrom = exchangeRatesParser
					.getExchangeRateByCurrency(from.name());
		}

		double exchangeRateTo = 1.0;
		if (CashFlowDAOCurrencyType.BGN != to) {
			exchangeRateTo = exchangeRatesParser.getExchangeRateByCurrency(to
					.name());
		}

		return exchangeRateFrom / exchangeRateTo;
	}

}