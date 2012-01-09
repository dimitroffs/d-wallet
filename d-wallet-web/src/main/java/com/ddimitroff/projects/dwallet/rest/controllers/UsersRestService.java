package com.ddimitroff.projects.dwallet.rest.controllers;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
	private ArrayList<String> apiKeys;

	@RequestMapping(method = RequestMethod.GET, value = "/users/get/{productId}")
	@ResponseBody
	public UserRO getUserById(@PathVariable String productId) {
		UserDAO dao = userManager.getUserByName(productId);
		UserRO ro = userManager.convert(dao);

		return ro;
	}

	// Content-Type: application/json needed
	// DWallet-API-Key: <api-key> needed
	@RequestMapping(method = RequestMethod.POST, value = "/users/register")
	@ResponseStatus(value = HttpStatus.OK)
	public void registerUser(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody UserRO ro)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != ro) {
				UserDAO daoToRegister = userManager.convert(ro);
				try {
					userManager.saveUser(daoToRegister);
				} catch (Exception e) {
					logger.error("Unable to register user " + daoToRegister.getEmail(), e);
					throw new DWalletResponseException("Unable to register user " + daoToRegister.getEmail());
				}
			} else {
				logger.error("Wrong request parameters for register of new user");
				throw new DWalletResponseException("Wrong request parameters for register of new user");
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
	public String loginUser(@RequestHeader(value = DWALLET_REQUEST_HEADER, required = false) String apiKey, @RequestBody UserRO ro)
			throws DWalletResponseException {
		if (isValidAPIKey(apiKey)) {
			if (null != ro) {
				UserDAO daoToLogin = userManager.getConvertedUser(ro);
				Token token = tokenGenerator.generate(daoToLogin);
				if (null != token) {
					return token.getId();
				} else {
					logger.error("Unable to generate token for user " + daoToLogin.getEmail());
					throw new DWalletResponseException("Unable to generate token for user " + daoToLogin.getEmail());
				}
			} else {
				logger.error("Wrong request parameters for login of user");
				throw new DWalletResponseException("Wrong request parameters for login of user");
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