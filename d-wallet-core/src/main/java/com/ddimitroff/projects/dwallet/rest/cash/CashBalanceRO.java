package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;

import com.ddimitroff.projects.dwallet.rest.response.Responsable;

/**
 * A DTO class for object/JSON mapping cash balance. It has the following
 * structure:<br>
 * {"currency":"1", "profit":"11.8", "cost":"11.8"}
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class CashBalanceRO implements Serializable, Responsable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Currency field */
  private int currency;

  /** Profit field */
  private double profit;

  /** Cost field */
  private double cost;

  /**
   * Default no argument constructor for creating new {@link CashBalanceRO}
   * object
   */
  public CashBalanceRO() {
  }

  /**
   * Constructor for creating new {@link CashBalanceRO} object
   * 
   * @param currency
   *          - currency to set
   * @param profit
   *          - profit to set
   * @param cost
   *          - cost to set
   */
  public CashBalanceRO(int currency, double profit, double cost) {
    this.currency = currency;
    this.profit = profit;
    this.cost = cost;
  }

  /**
   * @return the currency
   */
  public int getCurrency() {
    return currency;
  }

  /**
   * @param currency
   *          the currency to set
   */
  public void setCurrency(int currency) {
    this.currency = currency;
  }

  /**
   * @return the profit
   */
  public double getProfit() {
    return profit;
  }

  /**
   * @param profit
   *          the profit to set
   */
  public void setProfit(double profit) {
    this.profit = profit;
  }

  /**
   * @return the cost
   */
  public double getCost() {
    return cost;
  }

  /**
   * @param cost
   *          the cost to set
   */
  public void setCost(double cost) {
    this.cost = cost;
  }

}
