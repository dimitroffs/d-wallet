package com.ddimitroff.projects.dwallet.db.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;

/**
 * An entity class for object mapping to 'CASH_BALANCES' database table. It is
 * used for single cash balance.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Entity
@Table(name = "CASHBALANCES")
@NamedQueries({ @NamedQuery(name = CashBalance.GET_CASH_BALANCE_BY_USER, query = "SELECT cb FROM CashBalance cb WHERE cb.owner = :owner") })
public class CashBalance extends AbstractCashBalance implements Comparable<CashBalance> {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Named query identification constant */
  public static final String GET_CASH_BALANCE_BY_USER = "CashBalance.getCashBalanceByUser";

  /** Owner of cash balance */
  private User owner;

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
    super(currency, debit, credit);

    if (null == owner) {
      throw new IllegalArgumentException("Cash balance owner should be specified!");
    }

    this.owner = owner;
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

  // TODO use StringBuilder
  @Override
  public String toString() {
    return owner + " of single cash balance: (+) " + getDebit() + " (-) " + getCredit();
  }

  @Override
  public int compareTo(CashBalance o) {
    return this.owner.compareTo(o.getOwner());
  }

}
