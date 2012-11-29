package com.ddimitroff.projects.dwallet.managers.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddimitroff.projects.dwallet.db.dao.CashFlowDAO;
import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.CashFlowType;
import com.ddimitroff.projects.dwallet.enums.CashFlowsReportType;
import com.ddimitroff.projects.dwallet.managers.CashFlowManager;
import com.ddimitroff.projects.dwallet.rest.cash.CashFlowRO;
import com.ddimitroff.projects.dwallet.rest.cash.CashReportRO;

/**
 * Implementation class of {@link CashFlowManager} interface. It is used as
 * Spring component.
 * 
 * @author Dimitar Dimitrov
 * 
 */
@Service("cashFlowManager")
public class CashFlowManagerImpl extends BaseManagerImpl<CashFlow> implements CashFlowManager {
    
  /** Logger constant */
  private static final Logger LOG = Logger.getLogger(CashFlowManagerImpl.class);

  /** Negative period value for checking reports. Used for {@link Calendar} object */
  private static final int PERIOD = -1;  

  /** Injected {@link CashFlowDAO} component by Spring */
  @Autowired
  private CashFlowDAO cashFlowDao;

  @Override
  public void postConstruct() {
    baseDao = cashFlowDao;
  }

  public List<CashFlow> getCashFlowsByUser(User owner) {
    return cashFlowDao.getCashFlowsByUser(owner);
  }

  public CashFlowRO convert(CashFlow entity) {
    int cashFlowType = entity.getType().getIntType();
    int cashFlowCurrencyType = entity.getCurrencyType().getIntCurrencyCode();
    double cashFlowSum = entity.getSum();
    String cashFlowDescription = entity.getDescription();

    CashFlowRO ro = new CashFlowRO(cashFlowType, cashFlowCurrencyType, cashFlowSum, cashFlowDescription);
    ro.setCashFlowDate(entity.getCreated().getTime());

    return ro;
  }

  public CashFlow convert(CashFlowRO ro, User owner) {
    CashFlowType cashFlowType = CashFlowType.getType(ro.getCashFlowType());
    CashFlowCurrencyType cashFlowCurrencyType = CashFlowCurrencyType.getCurrencyType(ro.getCashFlowCurrency());
    double cashFlowSum = ro.getCashFlowSum();
    String cashFlowDescription = ro.getCashFlowDescription();

    CashFlow dao = new CashFlow(owner, cashFlowType, cashFlowCurrencyType, cashFlowSum, cashFlowDescription);

    return dao;
  }

  @Override
  public CashReportRO generateCashReportByUserAndType(User ownerArg, CashFlowsReportType cashFlowsReportTypeArg) {
    Date reportStartDate = getCashFlowsReportStartDate(cashFlowsReportTypeArg);
    if (null != reportStartDate) {
      List<CashFlow> cashFlowsForReport = cashFlowDao.getCashFlowsByUserAndDate(ownerArg, reportStartDate);
      if (null != cashFlowsForReport) {
        double[] cashFlowsProfitAndCost = calculateCashFlowsProfitAndCost(cashFlowsForReport);
        double cashFlowsProfit = cashFlowsProfitAndCost[0];
        double cashFlowsCost = cashFlowsProfitAndCost[1];
        ArrayList<CashFlowRO> cashFlows = getCashFlowsROArray(cashFlowsForReport);
        
        CashReportRO output = new CashReportRO(cashFlowsForReport.size(), cashFlowsProfit, cashFlowsCost, cashFlowsReportTypeArg, cashFlows);
        
        return output;
      }
    }
    
    return null;
  }

  /**
   * A helper method to get cash flows report start date
   * 
   * @param cashFlowsReportTypeArg
   *          - {@link CashFlowsReportType} object representing cash report type
   *          
   * @return {@link Date} object representing start date for report. End date is current one.
   */
  private Date getCashFlowsReportStartDate(CashFlowsReportType cashFlowsReportTypeArg) {
    Calendar reportStartDateCalendar = Calendar.getInstance();
    
    switch (cashFlowsReportTypeArg) {
        case DAILY: {
            reportStartDateCalendar.add(Calendar.DAY_OF_MONTH, PERIOD);
            break;
        }
        case WEEKLY: {
            reportStartDateCalendar.add(Calendar.WEEK_OF_MONTH, PERIOD);
            break;
        }
        case MONTHLY: {
            reportStartDateCalendar.add(Calendar.MONTH, PERIOD);
            break;
        }
        case MONTHLY_3: { 
            reportStartDateCalendar.add(Calendar.MONTH, 3 * PERIOD);
            break;
        }    
        case MONTHLY_6: {
            reportStartDateCalendar.add(Calendar.MONTH, 6 * PERIOD);
            break;
        }
        case YEARLY: {
            reportStartDateCalendar.add(Calendar.YEAR, PERIOD);
            break;
        }
        default: {
            LOG.error("Unable to create calendar for cash flows report start date.");
            return null;
        }    
    }
    
    return reportStartDateCalendar.getTime();
  }
  
  /**
   * A helper method to transform array of {@link CashFlow} entity objects to array of {@link CashFlowRO} rest objects
   * 
   * @param cashFlowsForReportArg - array of {@link CashFlow} objects to transform
   * 
   * @return transformed array of {@link CashFlowRO} objects
   */
  private ArrayList<CashFlowRO> getCashFlowsROArray(List<CashFlow> cashFlowsForReportArg) {
      ArrayList<CashFlowRO> output = new ArrayList<CashFlowRO>();
      
      for (CashFlow currentCashFlow : cashFlowsForReportArg) {
          CashFlowRO currentCashFlowRO = new CashFlowRO(currentCashFlow.getType().getIntType(), currentCashFlow.getCurrencyType().getIntCurrencyCode(), currentCashFlow.getSum(), currentCashFlow.getDescription());
          currentCashFlowRO.setCashFlowDate(currentCashFlow.getCreated().getTime());
          
          output.add(currentCashFlowRO);
      }
      
      return output;
  }
  
  /**
   * A helper method to calculate cash flows profit and cost
   * 
   * @param cashFlowsForReportArg - list of {@link CashFlow} objects to calculate
   * 
   * @return double[2] array as follows: double[0] - cash flows profit, double[1] - cash flows cost
   */
  private double[] calculateCashFlowsProfitAndCost(List<CashFlow> cashFlowsForReportArg) {
      double[] output = new double[2];
      double calculatedProfit = 0;
      double calculatedCost = 0;
      
      for (CashFlow currentCashFlow : cashFlowsForReportArg) {
          if (CashFlowType.PROFIT.equals(currentCashFlow.getType())) {
              calculatedProfit += currentCashFlow.getSum();
          } else if (CashFlowType.COST.equals(currentCashFlow.getType())) {
              calculatedCost += currentCashFlow.getSum();
          } else {
              LOG.error("Unable to recognize cash flow type. [type = " + currentCashFlow.getType() + "]");
          }
      }
      
      output[0] = calculatedProfit;
      output[1] = calculatedCost;
      
      return output;
  }

}
