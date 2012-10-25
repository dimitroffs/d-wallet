package com.ddimitroff.projects.dwallet.managers;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.rest.cash.CashBalanceRO;

/**
 * Interface for {@link CashBalance} entity object manager. Used to specify
 * methods related only to {@link CashBalance} entity object.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public interface CashBalanceManager extends BaseManager<CashBalance> {

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

  /**
   * A method for converting {@link CashBalance} entity object to
   * {@link CashBalanceRO} object
   * 
   * @param entity
   *          - {@link CashBalance} entity object to convert
   * 
   * @return converted {@link CashBalanceRO} object
   */
  public CashBalanceRO convert(CashBalance entity);

}
