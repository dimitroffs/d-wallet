package com.ddimitroff.projects.dwallet.db.dao;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;

/**
 * Interface for {@link CashBalance} entity object. Used to specify methods
 * related only to {@link CashBalance} entity object.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public interface CashBalanceDAO extends BaseDAO<CashBalance> {

  /**
   * A method for getting {@link CashBalance} entity object by specifying its
   * owner
   * 
   * @param owner
   *          - owner of {@link CashBalance} entity object
   * 
   * @return {@link CashBalance} entity object of specified user, {@code null}
   *         otherwise
   */
  public CashBalance getCashBalanceByUser(User owner);

}
