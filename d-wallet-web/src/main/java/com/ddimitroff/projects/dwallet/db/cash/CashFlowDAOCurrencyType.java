package com.ddimitroff.projects.dwallet.db.cash;

public enum CashFlowDAOCurrencyType {
	BGN(1), USD(2), EUR(3);

	private final int currencyCode;

	CashFlowDAOCurrencyType(int currencyCode) {
		this.currencyCode = currencyCode;
	}

	public int getIntCurrencyCode() {
		return currencyCode;
	}

	public static final CashFlowDAOCurrencyType getCurrencyType(int currencyCode) {
		for (CashFlowDAOCurrencyType current : CashFlowDAOCurrencyType.values()) {
			if (current.getIntCurrencyCode() != currencyCode) {
				continue;
			} else {
				return current;
			}
		}

		return null;
	}

}
