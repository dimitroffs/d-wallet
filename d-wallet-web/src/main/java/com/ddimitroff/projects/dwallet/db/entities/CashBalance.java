package com.ddimitroff.projects.dwallet.db.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;

@Entity
@Table(name = "CASH_BALANCES")
@NamedQueries({
// @NamedQuery(name = CashFlowDAO.GET_USER_BY_CREDENTIALS, query =
// "SELECT user FROM UserDAO user WHERE user.email = :email AND user.hashPassword = :password"),
@NamedQuery(name = CashBalance.GET_CASH_BALANCE_BY_USER, query = "SELECT cashBalance FROM CashBalance cashBalance WHERE cashBalance.owner = :owner") })
public class CashBalance extends BaseEntity implements Comparable<CashBalance>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_CASH_BALANCE_BY_USER = "CashBalanceDAO.getCashBalanceByUser";
	// public static final String GET_USER_BY_CREDENTIALS =
	// "User.getUserByCredentials";

	@ManyToOne
	private User owner;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private CashFlowCurrencyType currency;

	@Column
	private double debit;

	@Column
	private double credit;

	public CashBalance() {
	}

	public CashBalance(User owner, CashFlowCurrencyType currency, double debit, double credit) {
		if (null == owner) {
			throw new IllegalArgumentException("Cash balance owner should be specified!");
		}
		if (null == currency) {
			throw new IllegalArgumentException("Cash balance currency type should be specified!");
		}

		this.owner = owner;
		this.currency = currency;
		this.debit = debit;
		this.credit = credit;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public CashFlowCurrencyType getCurrency() {
		return currency;
	}

	public void setCurrency(CashFlowCurrencyType currency) {
		this.currency = currency;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(credit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		temp = Double.doubleToLongBits(debit);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CashBalance other = (CashBalance) obj;
		if (Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit))
			return false;
		if (currency != other.currency)
			return false;
		if (Double.doubleToLongBits(debit) != Double.doubleToLongBits(other.debit))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return owner + " balance: (+) " + debit + " (-) " + credit;
	}

	@Override
	public int compareTo(CashBalance o) {
		return this.owner.compareTo(o.getOwner());
	}

}
