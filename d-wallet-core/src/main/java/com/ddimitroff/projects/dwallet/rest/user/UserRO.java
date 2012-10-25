package com.ddimitroff.projects.dwallet.rest.user;

import java.io.Serializable;

/**
 * A DTO class for object/JSON mapping cash flows. It has the following
 * structure:<br>
 * {"username":"mykob.11@gmail.com", "hashPassword":"mykob.11",
 * "defaultCurrency":"1", "startupBalance":"1000.50"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class UserRO implements Serializable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Username field */
  private String username;

  /** Hash password field */
  private String hashPassword;

  /** Default currency field */
  private int defaultCurrency;

  /** Startup balance field */
  private double startupBalance;

  /**
   * Default no argument constructor for creating new {@link UserRO} object
   */
  public UserRO() {
  }

  /**
   * A constructor for creating new {@link UserRO} object
   * 
   * @param username
   *          - username to set
   * @param hashPassword
   *          - hashPassword to set
   * @param defaultCurrency
   *          - defaultCurrency to set
   * @param startupBalance
   *          - startupBalance to set
   */
  public UserRO(String username, String hashPassword, int defaultCurrency, double startupBalance) {
    this.username = username;
    this.hashPassword = hashPassword;
    this.defaultCurrency = defaultCurrency;
    this.startupBalance = startupBalance;
  }

  /**
   * @return the username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username
   *          the username to set
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return the hashPassword
   */
  public String getHashPassword() {
    return hashPassword;
  }

  /**
   * @param hashPassword
   *          the hashPassword to set
   */
  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  /**
   * @return the defaultCurrency
   */
  public int getDefaultCurrency() {
    return defaultCurrency;
  }

  /**
   * @param defaultCurrency
   *          the defaultCurrency to set
   */
  public void setDefaultCurrency(int defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  /**
   * @return the startupBalance
   */
  public double getStartupBalance() {
    return startupBalance;
  }

  /**
   * @param startupBalance
   *          the startupBalance to set
   */
  public void setStartupBalance(double startupBalance) {
    this.startupBalance = startupBalance;
  }

}
