package com.ddimitroff.projects.dwallet.rest.cash;

public class CashBalanceRO {

	private double profit;
	private double cost;

	public CashBalanceRO() {
	}

	public CashBalanceRO(double profit, double cost) {
		this.profit = profit;
		this.cost = cost;
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
