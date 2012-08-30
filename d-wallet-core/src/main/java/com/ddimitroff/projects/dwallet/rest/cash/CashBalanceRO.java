package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;

public class CashBalanceRO implements Serializable {

  private static final long serialVersionUID = 1L;

  private int currency;
  private double profit;
  private double cost;

  public CashBalanceRO() {
  }

  public CashBalanceRO(int currency, double profit, double cost) {
    this.currency = currency;
    this.profit = profit;
    this.cost = cost;
  }

  public int getCurrency() {
    return currency;
  }

  public void setCurrency(int currency) {
    this.currency = currency;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

}
