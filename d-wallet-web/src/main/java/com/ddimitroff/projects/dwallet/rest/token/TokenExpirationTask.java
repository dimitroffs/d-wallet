package com.ddimitroff.projects.dwallet.rest.token;

import org.apache.log4j.Logger;

/**
 * A class for expiration task of {@link Token} object
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class TokenExpirationTask implements Runnable {

  /** Logger object */
  private static final Logger logger = Logger.getLogger(TokenExpirationTask.class);

  /** Id of token to expire */
  private String tokenId;

  /** {@link TokenWatcher} object */
  private TokenWatcher tokenWatcher;

  /**
   * A constructor for new {@link TokenExpirationTask} object
   * 
   * @param tokenId
   *          - token id to set
   * @param tokenWatcher
   *          - token watcher to set
   */
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