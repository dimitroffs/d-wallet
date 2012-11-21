package com.ddimitroff.projects.dwallet.db.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An entity parent class for all database entity objects
 * 
 * @author Dimitar Dimitrov
 * 
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  /** Serial version UID constant */
  private static final long serialVersionUID = 1L;

  /** 'Id' column of all database objects */
  private int id;

  /** 'Created' column of all database objects */
  private Date created;

  /**
   * @return the id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public int getId() {
    return id;
  }

  /**
   * @param id
   *          the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the created
   */
  @Temporal(TemporalType.TIMESTAMP)
  @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
  public Date getCreated() {
    return created;
  }

  /**
   * @param created
   *          the created to set
   */
  public void setCreated(Date created) {
    this.created = created;
  }

}
