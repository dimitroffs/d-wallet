package com.ddimitroff.projects.dwallet.web.pages.cash;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ddimitroff.projects.dwallet.db.entities.CashBalance;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.CashBalanceManager;
import com.ddimitroff.projects.dwallet.managers.UserManager;

public class CashBalancePage extends WebPage {

  private static final long serialVersionUID = 1L;

  @SpringBean
  private CashBalanceManager cashBalanceManager;
  
  @SpringBean
  private UserManager userManager;

  public CashBalancePage(int userId) {
    User user = userManager.getById(User.class, userId);
    
    add(new Label("userEmail", user.getEmail()));

    CashBalance cashBalance = cashBalanceManager.getByUser(user);

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
