package com.ddimitroff.projects.dwallet.rest.token;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.scheduling.TaskScheduler;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.UserManager;

/**
 * A class for manipulating token objects
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class TokenGenerator {

  /** Logger object */
  private static final Logger logger = Logger.getLogger(TokenGenerator.class);

  /** {@link UserManager} object, set via Spring */
  private UserManager userManager;

  /** {@link TokenWatcher} object, set via Spring */
  private TokenWatcher tokenWatcher;

  /** {@link TaskScheduler} object, set via Spring as token scheduler */
  private TaskScheduler tokenScheduler;

  /**
   * A method for generating new {@link Token} object
   * 
   * @param userEntity
   *          - owner of token
   * 
   * @return newly created {@link Token} object of user, {@code null} otherwise
   */
  public Token generate(User userEntity) {
    if (null != userEntity) {
      if (isUserLoggedIn(userEntity)) {
        logger.error("User " + userEntity.getEmail() + " already has token for 'd-wallet' server operations");
        return null;
      } else {
        Token token = new Token(userEntity);
        tokenWatcher.addToken(token);
        tokenScheduler.schedule(new TokenExpirationTask(token.getId(), tokenWatcher), getTokenExpireDate());

        return token;
      }
    } else {
      throw new NullPointerException("Unable to generate token for NULL input user");
    }
  }

  /**
   * A method for checking if {@link User} object provided is currently logged
   * in
   * 
   * @param userEntity
   *          - user to check
   * 
   * @return {@code true} if user is logged in, {@code false} otherwise
   */
  private boolean isUserLoggedIn(User userEntity) {
    if (null != tokenWatcher.getTokenByUser(userEntity)) {
      return true;
    }

    return false;
  }

  /**
   * A method for converting {@link Token} object to {@link TokenRO} object
   * 
   * @param token
   *          - {@link Token} to convert to
   * 
   * @return converted {@link TokenRO} object
   */
  public TokenRO convert(Token token) {
    TokenRO output = new TokenRO(token.getId());

    return output;
  }

  /**
   * A method for converting {@link TokenRO} object to {@link Token} object
   * 
   * @param token
   *          - {@link TokenRO} to convert to
   * 
   * @return converted {@link Token} object
   */
  public Token getConvertedToken(TokenRO tokenRO) {
    Token output = tokenWatcher.getTokenById(tokenRO.getTokenId());

    return output;
  }

  /**
   * A method for getting token expiration date
   * 
   * @return expiration {@link Date} object of token
   */
  private Date getTokenExpireDate() {
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.MINUTE, Token.TOKEN_TIMEOUT);

    return cal.getTime();
  }

  /**
   * @return the userManager
   */
  public UserManager getUserManager() {
    return userManager;
  }

  /**
   * @param userManager
   *          the userManager to set
   */
  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }

  /**
   * @return the tokenWatcher
   */
  public TokenWatcher getTokenWatcher() {
    return tokenWatcher;
  }

  /**
   * @param tokenWatcher
   *          the tokenWatcher to set
   */
  public void setTokenWatcher(TokenWatcher tokenWatcher) {
    this.tokenWatcher = tokenWatcher;
  }

  /**
   * @return the tokenScheduler
   */
  public TaskScheduler getTokenScheduler() {
    return tokenScheduler;
  }

  /**
   * @param tokenScheduler
   *          the tokenScheduler to set
   */
  public void setTokenScheduler(TaskScheduler tokenScheduler) {
    this.tokenScheduler = tokenScheduler;
  }

}
