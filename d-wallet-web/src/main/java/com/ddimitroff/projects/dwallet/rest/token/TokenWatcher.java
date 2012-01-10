package com.ddimitroff.projects.dwallet.rest.token;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.UserDAO;

public class TokenWatcher {

	private static final Logger logger = Logger.getLogger(TokenWatcher.class);

	private HashMap<String, Token> activeTokens;

	public void init() {
		long start = System.nanoTime();
		activeTokens = new HashMap<String, Token>();
		logger.info("Initializing 'd-wallet' token watcher finished in " + (System.nanoTime() - start) / 1000000 + " ms.");
	}

	public void destroy() {
		if (!activeTokens.isEmpty()) {
			activeTokens = null; // left for GC
		}
		logger.info("Shutting down 'd-wallet' token watcher...");
	}

	public HashMap<String, Token> getActiveTokens() {
		return activeTokens;
	}

	public void addToken(Token tokenToAdd) {
		activeTokens.put(tokenToAdd.getId(), tokenToAdd);
		logger.info("Token " + tokenToAdd.getId() + " successfully added to 'd-wallet' token watcher.");
	}

	public Token getTokenById(String tokenId) {
		return activeTokens.get(tokenId);
	}

	public void removeToken(String tokenId) {
		activeTokens.remove(tokenId);
		logger.info("Token " + tokenId + " successfully removed from 'd-wallet' token watcher.");
	}

	public Token getTokenByUser(UserDAO user) {
		for (Token current : activeTokens.values()) {
			if (current.getOwner().getEmail().equals(user.getEmail())) {
				return current;
			}
		}

		return null;
	}

}
