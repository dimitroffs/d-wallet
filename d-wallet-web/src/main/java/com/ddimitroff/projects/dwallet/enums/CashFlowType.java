package com.ddimitroff.projects.dwallet.enums;

/**
 * Enumeration class for defining cash flow types
 * 
 * @author Dimitar Dimitrov
 * 
 */
public enum CashFlowType {
  PROFIT(1), COST(2);

  /** Cash flow type as integer value */
  private final int type;

  /**
   * {@link CashFlowType} private constructor
   * 
   * @param type
   *          - integer value type to set
   */
  private CashFlowType(int type) {
    this.type = type;
  }

  /**
   * A method for returning integer value of specified cash flow type
   * 
   * @return integer value of cash flow type
   */
  public int getIntType() {
    return type;
  }

  /**
   * A method for returning cash flow type by specified integer value type code
   * 
   * @param type
   *          - specified cash flow type code
   * 
   * @return {@link CashFlowType} enumeration object
   */
  public static final CashFlowType getType(int type) {
    for (CashFlowType current : CashFlowType.values()) {
      if (current.getIntType() == type) {
        return current;
      }
    }

    return null;
  }

}
