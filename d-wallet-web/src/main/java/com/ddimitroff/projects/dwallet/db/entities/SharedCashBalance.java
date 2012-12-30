package com.ddimitroff.projects.dwallet.db.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;

/**
 * An entity class for object mapping to 'SHARED_CASH_BALANCES' database table.
 * It is used for shared cash balance.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Entity
@Table(name = "SHAREDCASHBALANCES")
public class SharedCashBalance extends AbstractCashBalance {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Owners of shared cash balance */
  private List<User> owners;

  /**
   * {@link SharedCashBalance} default constructor
   */
  public SharedCashBalance() {
  }

  /**
   * {@link SharedCashBalance} parametrized constructor
   * 
   * @param owners
   *          - owners to be set
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
  public SharedCashBalance(List<User> owners, CashFlowCurrencyType currency, double debit, double credit) {
    super(currency, debit, credit);

    if (null == owners) {
      throw new IllegalArgumentException("Shared cash balance owners should be specified!");
    }

    this.owners = owners;
  }

  /**
   * @return the owners
   */
  @ManyToMany
  public List<User> getOwners() {
    return owners;
  }

  /**
   * @param owners
   *          the owners to set
   */
  public void setOwners(List<User> owners) {
    this.owners = owners;
  }

  // TODO use StringBuilder
  @Override
  public String toString() {
    return "Shared cash balance: (+) " + getDebit() + " (-) " + getCredit();
  }

}
