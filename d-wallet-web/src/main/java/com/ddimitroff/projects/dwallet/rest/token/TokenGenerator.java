package com.ddimitroff.projects.dwallet.rest.token;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;

public class TokenGenerator {

	private static final Logger logger = Logger.getLogger(TokenGenerator.class);

	private UserDAOManager userManager;
	private TokenWatcher tokenWatcher;

	public Token generate(UserDAO dao) {

		if (null != dao) {
			if (isUserLoggedIn(dao)) {
				logger.error("User " + dao.getEmail() + " already has token for 'd-wallet' server operations");
				return null;
			} else {
				Token token = new Token(dao);
				tokenWatcher.addToken(token);

				return token;
			}
		} else {
			throw new NullPointerException("Unable to generate token for NULL input user");
		}
	}

	private boolean isUserLoggedIn(UserDAO dao) {
		if (null != tokenWatcher.getTokenByUser(dao)) {
			return true;
		}

		return false;
	}

	public TokenRO convert(Token token) {
		TokenRO output = new TokenRO(token.getId());

		return output;
	}

	public Token getConvertedToken(TokenRO tokenRO) {
		Token output = tokenWatcher.getTokenById(tokenRO.getToken());

		return output;
	}

	public UserDAOManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDAOManager userManager) {
		this.userManager = userManager;
	}

	public TokenWatcher getTokenWatcher() {
		return tokenWatcher;
	}

	public void setTokenWatcher(TokenWatcher tokenWatcher) {
		this.tokenWatcher = tokenWatcher;
	}

}
