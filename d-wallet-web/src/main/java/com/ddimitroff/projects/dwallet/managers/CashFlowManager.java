package com.ddimitroff.projects.dwallet.managers;

import java.util.List;

import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowsReportType;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashReportRO;

/**
 * Interface for {@link CashFlow} entity object manager. Used to specify methods
 * related only to {@link CashFlow} entity object.
 * 
 * @author Dimitar Dimitrov
 * 
 */
public interface CashFlowManager extends BaseManager<CashFlow> {

  /**
   * A method for getting a list of {@link CashFlow} entity objects by
   * specifying their owner
   * 
   * @param owner
   *          - owner of {@link CashFlow} entity objects
   * 
   * @return a list of {@link CashFlow} entity objects of specified user,
   *         {@code null} otherwise
   */
  public List<CashFlow> getCashFlowsByUser(User owner);

  /**
   * A method for converting {@link CashFlow} entity object to
   * {@link CashFlowRO} object
   * 
   * @param entity
   *          - {@link CashFlow} entity object to convert
   * 
   * @return converted {@link CashFlowRO} object
   */
  public CashFlowRO convert(CashFlow entity);

  /**
   * A method for converting {@link CashFlowRO} object to {@link CashFlow}
   * entity object. Used for storing {@link CashFlowRO} object to database.
   * 
   * @param ro
   *          - {@link CashFlowRO} object to convert
   * @param owner
   *          - cash flow user owner
   * 
   * @return converted {@link CashFlow} entity object
   */
  public CashFlow convert(CashFlowRO ro, User owner);

  /**
   * A method for generating specified cash report type for selected user
   * 
   * @param ownerArg
   *          - owner of cash report
   * @param cashFlowsReportTypeArg
   *          - {@link CashFlowsReportType} object specifying cash report type
   * 
   * @return {@link CashReportRO} object if report is successfully generated, {@code null} otherwise
   */
  public CashReportRO generateCashReportByUserAndType(User ownerArg, CashFlowsReportType cashFlowsReportTypeArg);

}
