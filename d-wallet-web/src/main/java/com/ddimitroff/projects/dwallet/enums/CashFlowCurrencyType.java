package com.ddimitroff.projects.dwallet.enums;

public enum CashFlowCurrencyType {
  BGN(1), USD(2), EUR(3);

  private final int currencyCode;

  private CashFlowCurrencyType(int currencyCode) {
    this.currencyCode = currencyCode;
  }

  public int getIntCurrencyCode() {
    return currencyCode;
  }

  public static final CashFlowCurrencyType getCurrencyType(int currencyCode) {
    for (CashFlowCurrencyType current : CashFlowCurrencyType.values()) {
      if (current.getIntCurrencyCode() == currencyCode) {
        return current;
      }
    }

    return null;
  }

}
