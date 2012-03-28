package com.ddimitroff.projects.dwallet.rest.token;

import org.apache.log4j.Logger;

public class TokenExpirationTask implements Runnable {

	private static final Logger logger = Logger.getLogger(TokenExpirationTask.class);

	private String tokenId;
	private TokenWatcher tokenWatcher;

	public TokenExpirationTask(String tokenId, TokenWatcher tokenWatcher) {
		this.tokenId = tokenId;
		this.tokenWatcher = tokenWatcher;
		logger.info("Successfully generated expiration task for token: " + tokenId);
	}

	@Override
	public void run() {
		tokenWatcher.removeToken(tokenId);
		logger.info("Expiration task for token " + tokenId + " successfully executed");
	}

	public String getTokenId() {
		return tokenId;
	}

}