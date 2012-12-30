package com.ddimitroff.projects.dwallet.db.entities;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;

/**
 * An abstract entity class for object mapping cash balances database tables.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@MappedSuperclass
public class AbstractCashBalance extends BaseEntity {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Cash balance' currency */
  private CashFlowCurrencyType currency;

  /** Cash balance' debit value */
  private double debit;

  /** Cash balance' credit value */
  private double credit;

  /**
   * {@link AbstractCashBalance} default constructor
   */
  public AbstractCashBalance() {
  }

  /**
   * {@link AbstractCashBalance} parametrized constructor
   * 
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
  public AbstractCashBalance(CashFlowCurrencyType currency, double debit, double credit) {
    if (null == currency) {
      throw new IllegalArgumentException("Cash balance currency type should be specified!");
    }
    
    this.currency = currency;
    this.debit = debit;
    this.credit = credit;
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
    AbstractCashBalance other = (AbstractCashBalance) obj;
    if (Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit))
      return false;
    if (currency != other.currency)
      return false;
    if (Double.doubleToLongBits(debit) != Double.doubleToLongBits(other.debit))
      return false;
    
    return true;
  }

  @Override
  public String toString() {
    return "Cash balance: (+) " + debit + " (-) " + credit;
  }

}
