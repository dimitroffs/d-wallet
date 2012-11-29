package com.ddimitroff.projects.dwallet.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;

/**
 * An entity class for object mapping to 'CASH_BALANCES' database table.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Entity
@Table(name = "CASH_BALANCES")
@NamedQueries({ @NamedQuery(name = CashBalance.GET_CASH_BALANCE_BY_USER, query = "SELECT cb FROM CashBalance cb WHERE cb.owner = :owner") })
public class CashBalance extends BaseEntity implements Comparable<CashBalance>, Serializable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Named query identification constant */
  public static final String GET_CASH_BALANCE_BY_USER = "CashBalance.getCashBalanceByUser";

  /** Owner of cash balance */
  private User owner;

  /** Cash balance' currency */
  private CashFlowCurrencyType currency;

  /** Cash balance' debit value */
  private double debit;

  /** Cash balance' credit value */
  private double credit;

  /**
   * {@link CashBalance} default constructor
   */
  public CashBalance() {
  }

  /**
   * {@link CashBalance} parametrized constructor
   * 
   * @param owner
   *          - owner to be set
   * @param currency
   *          - currency to be set
   * @param debit
   *          - startup debit value to be set
   * @param credit
   *          - startup credit value to be set
   * 
   * @throws IllegalArgumentException
   *           if cash balance owner is not specified or cash default currency
   *           is not specified
   */
  public CashBalance(User owner, CashFlowCurrencyType currency, double debit, double credit) {
    if (null == owner) {
      throw new IllegalArgumentException("Cash balance owner should be specified!");
    }
    if (null == currency) {
      throw new IllegalArgumentException("Cash balance currency type should be specified!");
    }

    this.owner = owner;
    this.currency = currency;
    this.debit = debit;
    this.credit = credit;
  }

  /**
   * @return the owner
   */
  @ManyToOne
  public User getOwner() {
    return owner;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * @return the currency
   */
  @Column(length = 32)
  @Enumerated(EnumType.STRING)
  public CashFlowCurrencyType getCurrency() {
    return currency;
  }

  /**
   * @param currency
   *          the currency to set
   */
  public void setCurrency(CashFlowCurrencyType currency) {
    this.currency = currency;
  }

  /**
   * @return the debit
   */
  @Column
  public double getDebit() {
    return debit;
  }

  /**
   * @param debit
   *          the debit to set
   */
  public void setDebit(double debit) {
    this.debit = debit;
  }

  /**
   * @return the credit
   */
  @Column
  public double getCredit() {
    return credit;
  }

  /**
   * @param credit
   *          the credit to set
   */
  public void setCredit(double credit) {
    this.credit = credit;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    long temp;
    temp = Double.doubleToLongBits(credit);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((currency == null) ? 0 : currency.hashCode());
    temp = Double.doubleToLongBits(debit);
    result = prime * result + (int) (temp ^ (temp >>> 32));
    result = prime * result + ((owner == null) ? 0 : owner.hashCode());
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
    CashBalance other = (CashBalance) obj;
    if (Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit))
      return false;
    if (currency != other.currency)
      return false;
    if (Double.doubleToLongBits(debit) != Double.doubleToLongBits(other.debit))
      return false;
    if (owner == null) {
      if (other.owner != null)
        return false;
    } else if (!owner.equals(other.owner))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return owner + " balance: (+) " + debit + " (-) " + credit;
  }

  @Override
  public int compareTo(CashBalance o) {
    return this.owner.compareTo(o.getOwner());
  }

}
