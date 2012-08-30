package com.ddimitroff.projects.dwallet.rest.cash;

import java.io.Serializable;

public class CashFlowRO implements Serializable {

  private static final long serialVersionUID = 1L;

  private int cashFlowType;
  private int cashFlowCurrency;
  private double cashFlowSum;

  public CashFlowRO() {
  }

  public CashFlowRO(int cashFlowType, int cashFlowCurrency, double cashFlowSum) {
    this.cashFlowType = cashFlowType;
    this.cashFlowCurrency = cashFlowCurrency;
    this.cashFlowSum = cashFlowSum;
  }

  public int getCashFlowType() {
    return cashFlowType;
  }

  public void setCashFlowType(int cashFlowType) {
    this.cashFlowType = cashFlowType;
  }

  public int getCashFlowCurrency() {
    return cashFlowCurrency;
  }

  public void setCashFlowCurrency(int cashFlowCurrency) {
    this.cashFlowCurrency = cashFlowCurrency;
  }

  public double getCashFlowSum() {
    return cashFlowSum;
  }

  public void setCashFlowSum(double cashFlowSum) {
    this.cashFlowSum = cashFlowSum;
  }

}
