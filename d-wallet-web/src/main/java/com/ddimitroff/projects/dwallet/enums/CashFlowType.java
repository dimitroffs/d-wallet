package com.ddimitroff.projects.dwallet.enums;

public enum CashFlowType {
  PROFIT(1), COST(2);

  private final int type;

  private CashFlowType(int type) {
    this.type = type;
  }

  public int getIntType() {
    return type;
  }

  public static final CashFlowType getType(int type) {
    for (CashFlowType current : CashFlowType.values()) {
      if (current.getIntType() == type) {
        return current;
      }
    }

    return null;
  }

}
