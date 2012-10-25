package com.ddimitroff.projects.dwallet.rest.token;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.entities.User;

/**
 * A class for managing active tokens, used in D-Wallet API
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class TokenWatcher {

  /** Logger object */
  private static final Logger logger = Logger.getLogger(TokenWatcher.class);

  /** Active tokens, created in D-Wallet system */
  private HashMap<String, Token> activeTokens;

  /**
   * A method for initializing {@link TokenWatcher} object, called from Spring
   */
  public void init() {
    long start = System.nanoTime();
    activeTokens = new HashMap<String, Token>();
    logger.info("Initializing 'd-wallet' token watcher finished in " + (System.nanoTime() - start) / 1000000 + " ms.");
  }

  /**
   * A method for destroying {@link TokenWatcher} object, called from Spring
   */
  public void destroy() {
    if (!activeTokens.isEmpty()) {
      activeTokens = null; // left for GC
    }
    logger.info("Shutting down 'd-wallet' token watcher...");
  }

  /**
   * An active tokens getter method
   * 
   * @return activeTokens {@link HashMap} object
   */
  public HashMap<String, Token> getActiveTokens() {
    return activeTokens;
  }

  /**
   * A method for adding new {@link Token} object to active tokens
   * 
   * @param tokenToAdd
   *          - {@link Token} object to add
   */
  public void addToken(Token tokenToAdd) {
    activeTokens.put(tokenToAdd.getId(), tokenToAdd);
    logger.info("Token " + tokenToAdd.getId() + " successfully added to 'd-wallet' token watcher.");
  }

  /**
   * A method for getting {@link Token} object by specifying its id
   * 
   * @param tokenId
   *          - {@link String} object representing token id to get
   * 
   * @return {@link Token} from active tokens if any, {@code null} otherwise
   */
  public Token getTokenById(String tokenId) {
    return activeTokens.get(tokenId);
  }

  /**
   * A method for removing {@link Token} object from active tokens by specifying
   * its id
   * 
   * @param tokenId
   *          - {@link String} object representing token id to remove
   */
  public void removeToken(String tokenId) {
    activeTokens.remove(tokenId);
    logger.info("Token " + tokenId + " successfully removed from 'd-wallet' token watcher.");
  }

  /**
   * A method for getting {@link Token} object by specifying its owner
   * 
   * @param user
   *          - {@link User} object representing owner of active token
   * 
   * @return {@link Token} from active tokens if any, {@code null} otherwise
   */
  public Token getTokenByUser(User user) {
    for (Token current : activeTokens.values()) {
      if (current.getOwner().getEmail().equals(user.getEmail())) {
        return current;
      }
    }

    return null;
  }

}
