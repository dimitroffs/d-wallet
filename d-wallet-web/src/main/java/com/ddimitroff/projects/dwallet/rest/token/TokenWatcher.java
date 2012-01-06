package com.ddimitroff.projects.dwallet.rest.token;

import org.apache.log4j.Logger;

public class TokenWatcher {

	private static final Logger logger = Logger.getLogger(TokenWatcher.class);

	public void init() {
		long start = System.nanoTime();

		// TODO

		logger.info("Initializing 'd-wallet' token watcher finished in " + (System.nanoTime() - start) / 1000000 + " ms.");
	}

	public void destroy() {
		logger.info("Shutting down 'd-wallet' token watcher...");
	}

}
