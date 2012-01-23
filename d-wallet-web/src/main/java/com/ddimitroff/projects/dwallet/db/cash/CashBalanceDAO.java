package com.ddimitroff.projects.dwallet.db.cash;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.db.user.UserDAO;

@Entity
@Table(name = "CASH_BALANCES")
@NamedQueries({
// @NamedQuery(name = CashFlowDAO.GET_USER_BY_CREDENTIALS, query =
// "SELECT user FROM UserDAO user WHERE user.email = :email AND user.hashPassword = :password"),
@NamedQuery(name = CashBalanceDAO.GET_CASH_BALANCE_BY_USER, query = "SELECT cashBalance FROM CashBalanceDAO cashBalance WHERE cashBalance.owner = :owner") })
public class CashBalanceDAO implements Comparable<CashBalanceDAO>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_CASH_BALANCE_BY_USER = "CashBalanceDAO.getCashBalanceByUser";
	// public static final String GET_USER_BY_CREDENTIALS =
	// "User.getUserByCredentials";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private UserDAO owner;

	@Column
	private double debit;

	@Column
	private double credit;

	public CashBalanceDAO() {
	}

	public CashBalanceDAO(UserDAO owner, double debit, double credit) {
		if (null == owner) {
			throw new IllegalArgumentException("Cash balance owner should be specified!");
		}

		this.owner = owner;
		this.debit = debit;
		this.credit = credit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserDAO getOwner() {
		return owner;
	}

	public void setOwner(UserDAO owner) {
		this.owner = owner;
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
		CashBalanceDAO other = (CashBalanceDAO) obj;
		if (Double.doubleToLongBits(credit) != Double.doubleToLongBits(other.credit))
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
	public int compareTo(CashBalanceDAO o) {
		return this.owner.compareTo(o.getOwner());
	}

}