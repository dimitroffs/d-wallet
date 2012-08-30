package com.ddimitroff.projects.dwallet.rest.user;

import java.io.Serializable;

/*
 * {"username":"mykob.11@gmail.com", "hashPassword":"mykob.11", "defaultCurrency":"1", "startupBalance":"1000.50"}
 */
public class UserRO implements Serializable {

  private String username;
  private String hashPassword;
  private int defaultCurrency;
  private double startupBalance;

  public UserRO() {
  }

  public UserRO(String username, String hashPassword, int defaultCurrency, double startupBalance) {
    this.username = username;
    this.hashPassword = hashPassword;
    this.defaultCurrency = defaultCurrency;
    this.startupBalance = startupBalance;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getHashPassword() {
    return hashPassword;
  }

  public void setHashPassword(String hashPassword) {
    this.hashPassword = hashPassword;
  }

  public int getDefaultCurrency() {
    return defaultCurrency;
  }

  public void setDefaultCurrency(int defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  public double getStartupBalance() {
    return startupBalance;
  }

  public void setStartupBalance(double startupBalance) {
    this.startupBalance = startupBalance;
  }

}
