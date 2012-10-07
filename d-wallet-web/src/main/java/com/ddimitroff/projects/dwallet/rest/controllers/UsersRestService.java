package com.ddimitroff.projects.dwallet.rest.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.rest.DWalletRestUtils;
import com.ddimitroff.projects.dwallet.rest.exception.DWalletResponseException;
import com.ddimitroff.projects.dwallet.rest.token.Token;
import com.ddimitroff.projects.dwallet.rest.token.TokenGenerator;
import com.ddimitroff.projects.dwallet.rest.token.TokenRO;
import com.ddimitroff.projects.dwallet.rest.token.TokenWatcher;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

@Controller
public class UsersRestService {

  private static final Logger logger = Logger.getLogger(UsersRestService.class);

  @Autowired
  private UserManager userManager;

  @Autowired
  private CashBalanceManager cashBalanceManager;

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  private TokenWatcher tokenWatcher;

  @Autowired
  private ArrayList<String> apiKeys;

  // DWallet-API-Key: <api-key> needed
  @RequestMapping(method = RequestMethod.POST, value = "/users/get")
  @ResponseBody
  public UserRO getActiveUserByTokenId(
      @RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
      @RequestBody TokenRO tokenRO) throws DWalletResponseException {
    if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
      if (null != tokenRO) {
        Token token = tokenWatcher.getTokenById(tokenRO.getTokenId());
        if (null != token) {
          User entity = token.getOwner();
          UserRO ro = userManager.convert(entity);
          return ro;
        } else {
          logger.error("Unable to find token with id " + tokenRO.getTokenId());
          throw new DWalletResponseException("Unable to find token with id " + tokenRO.getTokenId());
        }
      } else {
        logger.error("Wrong request body for getting user by token id");
        throw new DWalletResponseException("Wrong request body for getting user by token id");
      }
    } else {
      logger.error("Wrong 'd-wallet' API key");
      throw new DWalletResponseException("Wrong 'd-wallet' API key");
    }
  }

  // Content-Type: application/json needed
  // DWallet-API-Key: <api-key> needed
  @RequestMapping(method = RequestMethod.POST, value = "/users/register")
  @ResponseStatus(value = HttpStatus.OK)
  @Transactional
  public void registerUser(
      @RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
      @RequestBody UserRO userRO) throws DWalletResponseException {
    if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
      if (null != userRO) {
        User entityToRegister = userManager.convert(userRO);
        userManager.save(entityToRegister);

        CashBalance userStartBalance = null;
        if (entityToRegister.getStartupBalance() < 0) {
          userStartBalance = new CashBalance(entityToRegister, CashFlowCurrencyType.getCurrencyType(userRO
              .getDefaultCurrency()), 0, entityToRegister.getStartupBalance());
        } else {
          userStartBalance = new CashBalance(entityToRegister, CashFlowCurrencyType.getCurrencyType(userRO
              .getDefaultCurrency()), entityToRegister.getStartupBalance(), 0);
        }
        cashBalanceManager.save(userStartBalance);
      } else {
        logger.error("Wrong request body for register of new user");
        throw new DWalletResponseException("Wrong request body for register of new user");
      }
    } else {
      logger.error("Wrong 'd-wallet' API key");
      throw new DWalletResponseException("Wrong 'd-wallet' API key");
    }
  }

  // Content-Type: application/json needed
  // DWallet-API-Key: <api-key> needed
  @RequestMapping(method = RequestMethod.POST, value = "/users/login")
  @ResponseBody
  public TokenRO loginUser(
      @RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
      @RequestBody UserRO userRO) throws DWalletResponseException {
    if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
      if (null != userRO) {
        User entityToLogin = userManager.getConvertedUser(userRO);
        Token token = tokenGenerator.generate(entityToLogin);
        if (null != token) {
          TokenRO tokenRO = tokenGenerator.convert(token);
          return tokenRO;
        } else {
          logger.error("Unable to generate token for user " + entityToLogin.getEmail());
          throw new DWalletResponseException("Unable to generate token for user " + entityToLogin.getEmail());
        }
      } else {
        logger.error("Wrong request body for login of user");
        throw new DWalletResponseException("Wrong request body for login of user");
      }
    } else {
      logger.error("Wrong 'd-wallet' API key");
      throw new DWalletResponseException("Wrong 'd-wallet' API key");
    }
  }

  // DWallet-API-Key: <api-key> needed
  @RequestMapping(method = RequestMethod.POST, value = "/users/logout")
  @ResponseStatus(value = HttpStatus.OK)
  public void logoutUser(
      @RequestHeader(value = DWalletRestUtils.DWALLET_REQUEST_HEADER, required = false) String apiKey,
      @RequestBody TokenRO tokenRO) throws DWalletResponseException {
    if (DWalletRestUtils.isValidAPIKey(apiKey, apiKeys)) {
      if (null != tokenRO) {
        Token token = tokenWatcher.getTokenById(tokenRO.getTokenId());
        if (null != token) {
          tokenWatcher.removeToken(tokenRO.getTokenId());
        } else {
          logger.error("Token " + tokenRO.getTokenId() + " is not valid active token");
          throw new DWalletResponseException("Token " + tokenRO.getTokenId() + " is not valid active token");
        }
      } else {
        logger.error("Wrong request body for logout of user");
        throw new DWalletResponseException("Wrong request body for logout of user");
      }
    } else {
      logger.error("Wrong 'd-wallet' API key");
      throw new DWalletResponseException("Wrong 'd-wallet' API key");
    }
  }

}