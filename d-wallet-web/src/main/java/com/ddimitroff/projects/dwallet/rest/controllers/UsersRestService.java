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

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;
import com.ddimitroff.projects.dwallet.rest.exception.DWalletResponseException;
import com.ddimitroff.projects.dwallet.rest.token.Token;
import com.ddimitroff.projects.dwallet.rest.token.TokenGenerator;
import com.ddimitroff.projects.dwallet.rest.token.TokenRO;
import com.ddimitroff.projects.dwallet.rest.token.TokenWatcher;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/**
 * This is an example REST style MVC controller. It serves as an endpoint for
 * retrieving Product Objects.
 */
@Controller
public class UsersRestService {

	private static final Logger logger = Logger.getLogger(UsersRestService.class);
	private static final String DWALLET_REQUEST_HEADER = "DWallet-API-Key";

	@Autowired
	private UserDAOManager userManager;

	@Autowired
	private TokenGenerator tokenGenerator;

	@Autowired
	private TokenWatcher tokenWatcher;

	@Autowired
	private ArrayList<String> apiKeys;

	// DWallet-API-Key: <api-key> needed
	@RequestMapping(method = RequestMethod.POST, value = "/users/get")
	@ResponseBody
	public UserRO getActiveUserByTokenId(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody TokenRO tokenRO)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != tokenRO) {
				Token token = tokenWatcher.getTokenById(tokenRO.getToken());
				if (null != token) {
					UserDAO dao = token.getOwner();
					UserRO ro = userManager.convert(dao);
					return ro;
				} else {
					logger.error("Unable to find token with id " + tokenRO.getToken());
					throw new DWalletResponseException("Unable to find token with id " + tokenRO.getToken());
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
	public void registerUser(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody UserRO userRO)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != userRO) {
				UserDAO daoToRegister = userManager.convert(userRO);
				try {
					userManager.saveUser(daoToRegister);
				} catch (Exception e) {
					logger.error("Unable to register user " + daoToRegister.getEmail(), e);
					throw new DWalletResponseException("Unable to register user " + daoToRegister.getEmail());
				}
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
	public TokenRO loginUser(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody UserRO userRO)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != userRO) {
				UserDAO daoToLogin = userManager.getConvertedUser(userRO);
				Token token = tokenGenerator.generate(daoToLogin);
				if (null != token) {
					TokenRO tokenRO = tokenGenerator.convert(token);
					return tokenRO;
				} else {
					logger.error("Unable to generate token for user " + daoToLogin.getEmail());
					throw new DWalletResponseException("Unable to generate token for user " + daoToLogin.getEmail());
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
	public void logoutUser(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody TokenRO tokenRO)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != tokenRO) {
				Token token = tokenWatcher.getTokenById(tokenRO.getToken());
				if (null != token) {
					tokenWatcher.removeToken(tokenRO.getToken());
				} else {
					logger.error("Token " + tokenRO.getToken() + " is not valid active token");
					throw new DWalletResponseException("Token " + tokenRO.getToken() + " is not valid active token");
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

	private boolean isValidAPIKey(String apiKey) {
		if (null == apiKey) {
			return false;
		}

		return apiKeys.contains(apiKey);
	}
}