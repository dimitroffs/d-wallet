package com.ddimitroff.projects.dwallet.db.cash;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddimitroff.projects.dwallet.db.user.UserDAO;

@Entity
@Table(name = "CASH_FLOWS")
@NamedQueries({
// @NamedQuery(name = CashFlowDAO.GET_USER_BY_CREDENTIALS, query =
// "SELECT user FROM UserDAO user WHERE user.email = :email AND user.hashPassword = :password"),
@NamedQuery(name = CashFlowDAO.GET_CASH_FLOWS_BY_USER, query = "SELECT cashFlow FROM CashFlowDAO cashFlow WHERE cashFlow.owner = :owner ORDER BY cashFlow.date") })
public class CashFlowDAO implements Comparable<CashFlowDAO>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_CASH_FLOWS_BY_USER = "CashFlowDAO.getCashFlowsByUser";
	// public static final String GET_USER_BY_CREDENTIALS =
	// "User.getUserByCredentials";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne
	private UserDAO owner;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private CashFlowDAOType type;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private CashFlowDAOCurrencyType currencyType;

	@Column
	private double sum;

	@Column
	private Date date;

	public CashFlowDAO() {
	}

	public CashFlowDAO(UserDAO owner, CashFlowDAOType type, CashFlowDAOCurrencyType currencyType, double sum, Date date) {
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

	public CashFlowDAOType getType() {
		return type;
	}

	public void setType(CashFlowDAOType type) {
		this.type = type;
	}

	public CashFlowDAOCurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CashFlowDAOCurrencyType currencyType) {
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
	public int compareTo(CashFlowDAO o) {
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
		CashFlowDAO other = (CashFlowDAO) obj;
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
