package com.ddimitroff.projects.dwallet.rest.session;

import java.util.List;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.managers.impl.UserManagerImpl;

/**
 * A singleton Spring class component used to validate D-Wallet's mobile
 * administration users
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class DWalletApplicationSession {

  /** Logger constant */
  private static final Logger logger = Logger.getLogger(DWalletApplicationSession.class);

  /** Injected {@link UserManagerImpl} Spring component */
  private UserManager userManager;

  /**
   * Mobile administration users' list, set by Spring during application startup
   */
  private List<User> adminUsers;

  /**
   * @return the adminUsers
   */
  public List<User> getAdminUsers() {
    return adminUsers;
  }

  /**
   * @param adminUsers
   *          the adminUsers to set
   */
  public void setAdminUsers(List<User> adminUsers) {
    this.adminUsers = adminUsers;
  }

  /**
   * Initialization method for {@link DWalletApplicationSession} component class
   */
  public void init() {
    long start = System.nanoTime();
    validateAdminUsers(adminUsers);
    logger.info("Initializing 'd-wallet' application session finished in " + (System.nanoTime() - start) / 1000000
        + " ms.");
  }

  /**
   * A method for validating mobile administration users
   * 
   * @param adminUsers
   *          - mobile administration users to validate
   */
  private void validateAdminUsers(List<User> adminUsers) {
    for (int i = 0; i < adminUsers.size(); i++) {
      User currentAdmin = adminUsers.get(i);

      User entity = userManager.getUserByEmail(currentAdmin.getEmail());
      if (null != entity) {
        continue;
      } else {
        userManager.save(currentAdmin);
      }
    }
  }

  /**
   * Destroy method for {@link DWalletApplicationSession} component class
   */
  public void destroy() {
    logger.info("Shutting down 'd-wallet' application session...");
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

}
