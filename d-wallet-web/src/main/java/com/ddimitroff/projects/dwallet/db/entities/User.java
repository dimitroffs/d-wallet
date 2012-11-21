package com.ddimitroff.projects.dwallet.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.UserRole;

/**
 * An entity class for object mapping to 'USERS' database table.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Entity
@Table(name = "USERS")
@NamedQueries({
    @NamedQuery(name = User.GET_USER_BY_CREDENTIALS, query = "SELECT u FROM User u WHERE u.email = :email AND u.hashPassword = :password"),
    @NamedQuery(name = User.GET_USER_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email") })
public class User extends BaseEntity implements Comparable<User> {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Named query identification constant */
  public static final String GET_USER_BY_EMAIL = "User.getUserByEmail";

  /** Named query identification constant */
  public static final String GET_USER_BY_CREDENTIALS = "User.getUserByCredentials";

  /** Email of user */
  private String email;

  /** Password of user */
  private String hashPassword;

  /** User's role */
  private UserRole role;

  /** User's default currency */
  private CashFlowCurrencyType defaultCurrency;

  /** User's startup balance */
  private double startupBalance;

  /**
   * {@link User} default constructor
   */
  public User() {
  }

  /**
   * {@link User} parametrized constructor
   * 
   * @param email
   *          - email to set
   * @param role
   *          - role to set
   * @param defaultCurrency
   *          - default currency to set
   * @param startupBalance
   *          - startup balance to set
   * 
   * @throws IllegalArgumentException
   *           if user email is not specified, user role is not specified, user
   *           default currency is not specified.
   */
  public User(String email, UserRole role, CashFlowCurrencyType defaultCurrency, double startupBalance) {
    if (null == email) {
      throw new IllegalArgumentException("User name should be specified!");
    }
    if (null == role) {
      throw new IllegalArgumentException("User role should be specified!");
    }
    if (null == defaultCurrency) {
      throw new IllegalArgumentException("User default balance currency should be specified!");
    }

    this.email = email;
    this.role = role;
    this.defaultCurrency = defaultCurrency;
    this.startupBalance = startupBalance;
  }

  /**
   * @return the email
   */
  @Column(unique = true, length = 64)
  public String getEmail() {
    return email;
  }

  /**
   * @param email
   *          the email to set
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * @return the hashPassword
   */
  @Column(length = 64)
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
   * @return the role
   */
  @Column(length = 32)
  @Enumerated(EnumType.STRING)
  public UserRole getRole() {
    return role;
  }

  /**
   * @param role
   *          the role to set
   */
  public void setRole(UserRole role) {
    this.role = role;
  }

  /**
   * @return the defaultCurrency
   */
  @Column(length = 32)
  @Enumerated(EnumType.STRING)
  public CashFlowCurrencyType getDefaultCurrency() {
    return defaultCurrency;
  }

  /**
   * @param defaultCurrency
   *          the defaultCurrency to set
   */
  public void setDefaultCurrency(CashFlowCurrencyType defaultCurrency) {
    this.defaultCurrency = defaultCurrency;
  }

  /**
   * @return the startupBalance
   */
  @Column
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

  @Override
  public int compareTo(User user) {
    return this.getEmail().compareTo(user.getEmail());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(startupBalance);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((defaultCurrency == null) ? 0 : defaultCurrency.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    User other = (User) obj;
    if (Double.doubleToLongBits(startupBalance) != Double.doubleToLongBits(other.startupBalance))
      return false;
    if (defaultCurrency != other.defaultCurrency)
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (role != other.role)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return email + " [" + role + "]" + " has " + startupBalance + " balance in " + defaultCurrency;
  }

}
