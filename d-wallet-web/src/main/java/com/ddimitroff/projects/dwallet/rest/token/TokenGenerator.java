package com.ddimitroff.projects.dwallet.rest.token;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;

public class TokenGenerator {

	private UserDAOManager userManager;
	private TokenWatcher tokenWatcher;

//	public TokenGenerator(UserDAOManager userManager) {
//		if (null != userManager) {
//			this.userManager = userManager;
//		} else {
//			throw new NullPointerException("Error creating token generator object due to null user data access object manager.");
//		}
//	}

	public Token generate(String username, String hashPassword) {
		UserDAO dao = userManager.getUserByCredentialsEmail(username, hashPassword);

		if (null != dao) {
			if (isUserLoggedIn(dao)) {
				// TODO log user is currently logged in
				System.out.println("User logged in");
				return null;
			} else {
				Token token = new Token(dao);
				// Add token to token watcher

				return token;
			}
		} else {
			// TODO log
			System.out.println("null DAO");
			return null;
		}
	}

	private boolean isUserLoggedIn(UserDAO dao) {
		// TODO Check Token Watcher if user is currently holding another token
		// (getTokenByUser - TokenWatcher);
		return false;
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
