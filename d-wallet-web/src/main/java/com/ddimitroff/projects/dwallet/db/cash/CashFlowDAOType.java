package com.ddimitroff.projects.dwallet.db.cash;

public enum CashFlowDAOType {
	PROFIT(1), COST(2);

	private final int type;

	CashFlowDAOType(int type) {
		this.type = type;
	}

	public int getIntType() {
		return type;
	}

	public static final CashFlowDAOType getType(int type) {
		for (CashFlowDAOType current : CashFlowDAOType.values()) {
			if (current.getIntType() != type) {
				continue;
			} else {
				return current;
			}
		}

		return null;
	}

}
