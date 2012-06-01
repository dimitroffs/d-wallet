package com.ddimitroff.projects.dwallet.db.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.CashFlowType;

@Entity
@Table(name = "CASH_FLOWS")
@NamedQueries({
// @NamedQuery(name = CashFlowDAO.GET_USER_BY_CREDENTIALS, query =
// "SELECT user FROM UserDAO user WHERE user.email = :email AND user.hashPassword = :password"),
@NamedQuery(name = CashFlow.GET_CASH_FLOWS_BY_USER, query = "SELECT cashFlow FROM CashFlow cashFlow WHERE cashFlow.owner = :owner ORDER BY cashFlow.date") })
public class CashFlow extends BaseEntity implements Comparable<CashFlow> {

	private static final long serialVersionUID = 1L;

	public static final String GET_CASH_FLOWS_BY_USER = "CashFlowDAO.getCashFlowsByUser";
	// public static final String GET_USER_BY_CREDENTIALS =
	// "User.getUserByCredentials";

	@ManyToOne
	private User owner;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private CashFlowType type;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private CashFlowCurrencyType currencyType;

	@Column
	private double sum;

	@Column
	private Date date;

	public CashFlow() {
	}

	public CashFlow(User owner, CashFlowType type, CashFlowCurrencyType currencyType, double sum, Date date) {
		if (null == owner) {
			throw new IllegalArgumentException("Cash flow owner should be specified!");
		}
		if (null == type) {
			throw new IllegalArgumentException("Cash flow type should be specified!");
		}
		if (null == currencyType) {
			throw new IllegalArgumentException("Cash flow currency type should be specified!");
		}
		if (0 >= sum) {
			throw new IllegalArgumentException("Positive cash flow sum should be specified!");
		}
		if (null == date) {
			throw new IllegalArgumentException("Cash flow date should be specified!");
		}

		this.owner = owner;
		this.type = type;
		this.currencyType = currencyType;
		this.sum = sum;
		this.date = date;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public CashFlowType getType() {
		return type;
	}

	public void setType(CashFlowType type) {
		this.type = type;
	}

	public CashFlowCurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CashFlowCurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public int compareTo(CashFlow o) {
		return date.compareTo(o.getDate());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currencyType == null) ? 0 : currencyType.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		long temp;
		temp = Double.doubleToLongBits(sum);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		CashFlow other = (CashFlow) obj;
		if (currencyType != other.currencyType)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		if (Double.doubleToLongBits(sum) != Double.doubleToLongBits(other.sum))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return type + " [" + owner + ", " + currencyType + ", " + sum + ", " + date + "]";
	}

}
