package com.ddimitroff.projects.dwallet.db.entities;

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

/**
 * An entity class for object mapping to 'CASH_FLOWS' database table.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Entity
@Table(name = "CASHFLOWS")
@NamedQueries({
    @NamedQuery(name = CashFlow.GET_CASH_FLOWS_BY_USER, query = "SELECT cf FROM CashFlow cf WHERE cf.owner = :owner ORDER BY cf.created"),
    @NamedQuery(name = CashFlow.GET_CASH_FLOWS_BY_USER_AND_DATE, query = "SELECT cf FROM CashFlow cf WHERE cf.owner = :owner AND cf.created >= :created ORDER BY cf.created") })
public class CashFlow extends BaseEntity implements Comparable<CashFlow> {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** Named query identification constants */
  public static final String GET_CASH_FLOWS_BY_USER = "CashFlow.getCashFlowsByUser";
  public static final String GET_CASH_FLOWS_BY_USER_AND_DATE = "CashFlow.getCashFlowsByUserAndDate";

  /** Owner of cash flow */
  private User owner;

  /** Cash flow type */
  private CashFlowType type;

  /** Cash flow currency type */
  private CashFlowCurrencyType currencyType;

  /** Cash flow sum value */
  private double sum;

  /** Cash flow creation date */
  private String description;

  /**
   * {@link CashFlow} default constructor
   */
  public CashFlow() {
  }

  /**
   * {@link CashFlow} parametrized constructor
   * 
   * @param owner
   *          - owner to be set
   * @param type
   *          - cash flow type to be set
   * @param currencyType
   *          - cash flow currency type to be set
   * @param sum
   *          - cash flow sum to be set
   * 
   * @throws IllegalArgumentException
   *           if cash flow owner is not specified, cash flow type is not
   *           specified, cash flow currency type is not specified, cash flow
   *           sum is negative or equal to zero number value.
   */
  public CashFlow(User owner, CashFlowType type, CashFlowCurrencyType currencyType, double sum, String description) {
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

    this.owner = owner;
    this.type = type;
    this.currencyType = currencyType;
    this.sum = sum;
    this.description = description;
  }

  /**
   * @return the owner
   */
  @ManyToOne
  public User getOwner() {
    return owner;
  }

  /**
   * @param owner
   *          the owner to set
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * @return the type
   */
  @Column(length = 32)
  @Enumerated(EnumType.STRING)
  public CashFlowType getType() {
    return type;
  }

  /**
   * @param type
   *          the type to set
   */
  public void setType(CashFlowType type) {
    this.type = type;
  }

  /**
   * @return the currencyType
   */
  @Column(length = 32)
  @Enumerated(EnumType.STRING)
  public CashFlowCurrencyType getCurrencyType() {
    return currencyType;
  }

  /**
   * @param currencyType
   *          the currencyType to set
   */
  public void setCurrencyType(CashFlowCurrencyType currencyType) {
    this.currencyType = currencyType;
  }

  /**
   * @return the sum
   */
  @Column
  public double getSum() {
    return sum;
  }

  /**
   * @param sum
   *          the sum to set
   */
  public void setSum(double sum) {
    this.sum = sum;
  }

  /**
   * @return the description
   */
  @Column(length = 256)
  public String getDescription() {
    return description;
  }

  /**
   * @param description
   *          the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public int compareTo(CashFlow o) {
    return getCreated().compareTo(o.getCreated());
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((currencyType == null) ? 0 : currencyType.hashCode());
    result = prime * result + ((getCreated() == null) ? 0 : getCreated().hashCode());
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
    if (getCreated() == null) {
      if (other.getCreated() != null)
        return false;
    } else if (!getCreated().equals(other.getCreated()))
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
    return type + " [" + owner + ", " + currencyType + ", " + sum + "] created on " + getCreated();
  }

}
