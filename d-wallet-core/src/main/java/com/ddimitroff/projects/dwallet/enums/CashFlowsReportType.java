package com.ddimitroff.projects.dwallet.enums;

/**
 * Enumeration class for defining cash flows report types
 * 
 * @author Dimitar Dimitrov
 * 
 */
public enum CashFlowsReportType {
  DAILY(1), WEEKLY(2), MONTHLY(3), MONTHLY_3(4), MONTHLY_6(5), YEARLY(6);

  /** Cash flows report type as integer value */
  private final int type;

  /**
   * {@link CashFlowsReportType} private constructor
   * 
   * @param type
   *          - integer value type to set
   */
  private CashFlowsReportType(int type) {
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
   * A method for returning cash flows report type by specified integer value
   * type code
   * 
   * @param type
   *          - specified cash flows report type code
   * 
   * @return {@link CashFlowsReportType} enumeration object
   */
  public static final CashFlowsReportType getType(int type) {
    for (CashFlowsReportType current : CashFlowsReportType.values()) {
      if (current.getIntType() == type) {
        return current;
      }
    }

    return null;
  }

}
