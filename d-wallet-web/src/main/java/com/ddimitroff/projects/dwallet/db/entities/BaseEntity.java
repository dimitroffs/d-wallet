package com.ddimitroff.projects.dwallet.db.entities;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  
  //TODO insert created
  
  //TODO insert updated

  /**
   * @return the id
   */
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

}
