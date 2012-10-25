package com.ddimitroff.projects.dwallet.web.pages.cash;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.managers.impl.CashBalanceManagerImpl;
import com.ddimitroff.projects.dwallet.managers.impl.UserManagerImpl;

/**
 * A class representing Wicket page for cash balances
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class CashBalancePage extends WebPage {

  /** Serialization field */
  private static final long serialVersionUID = 1L;

  /** Injected {@link CashBalanceManagerImpl} object by Spring */
  @SpringBean
  private CashBalanceManager cashBalanceManager;

  /** Injected {@link UserManagerImpl} object by Spring */
  @SpringBean
  private UserManager userManager;

  /**
   * Constructor for creating new {@link CashBalancePage} object
   * 
   * @param userId
   *          - id of user
   */
  public CashBalancePage(int userId) {
    User user = userManager.getById(User.class, userId);

    add(new Label("userEmail", user.getEmail()));

    CashBalance cashBalance = cashBalanceManager.getCashBalanceByUser(user);

    if (null != cashBalance) {
      add(new Label("userBalance", String.valueOf(cashBalance.getDebit() - cashBalance.getCredit())));
      add(new Label("userProfit", String.valueOf(cashBalance.getDebit())));
      add(new Label("userCost", String.valueOf(cashBalance.getCredit())));
    } else {
      add(new Label("userBalance", "0.0"));
      add(new Label("userProfit", "0.0"));
      add(new Label("userCost", "0.0"));
    }
  }

}
