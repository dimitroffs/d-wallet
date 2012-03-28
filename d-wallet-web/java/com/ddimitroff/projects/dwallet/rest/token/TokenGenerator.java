package com.ddimitroff.projects.dwallet.rest.token;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;

import com.ddimitroff.projects.dwallet.db.user.UserDAO;
import com.ddimitroff.projects.dwallet.db.user.UserDAOManager;

public class TokenGenerator {

	private static final Logger logger = Logger.getLogger(TokenGenerator.class);
	private static final int TOKEN_EXPIRATION_INTERVAL = 1; // TODO

	private UserDAOManager userManager;
	private TokenWatcher tokenWatcher;
	private TaskScheduler tokenScheduler;

	public Token generate(UserDAO dao) {
		if (null != dao) {
			if (isUserLoggedIn(dao)) {
				logger.error("User " + dao.getEmail()
						+ " already has token for 'd-wallet' server operations");
				return null;
			} else {
				Token token = new Token(dao);
				tokenWatcher.addToken(token);
				tokenScheduler.schedule(new TokenExpirationTask(token.getId(),
						tokenWatcher), getTokenExpireDate());

				return token;
			}
		} else {
			throw new NullPointerException(
					"Unable to generate token for NULL input user");
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
		Token output = tokenWatcher.getTokenById(tokenRO.getTokenId());

		return output;
	}

	private Date getTokenExpireDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, TOKEN_EXPIRATION_INTERVAL);

		return cal.getTime();
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

	public TaskScheduler getTokenScheduler() {
		return tokenScheduler;
	}

	public void setTokenScheduler(TaskScheduler tokenScheduler) {
		this.tokenScheduler = tokenScheduler;
	}

}
