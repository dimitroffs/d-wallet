package com.ddimitroff.projects.dwallet.web.pages.cash;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ddimitroff.projects.dwallet.db.entities.CashFlow;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.CashFlowManager;

public class CashFlowsPage extends WebPage {

  private static final long serialVersionUID = 1L;

  @SpringBean
  private CashFlowManager cashFlowManager;

  public CashFlowsPage(User user) {
    add(new Label("userEmail", user.getEmail()));

    List<CashFlow> cashFlows = cashFlowManager.getCashFlowsByUser(user);

    add(new ListView<CashFlow>("flowsView", cashFlows) {

      private static final long serialVersionUID = 1L;

      protected void populateItem(ListItem<CashFlow> item) {
        final CashFlow flow = (CashFlow) item.getModelObject();
        item.add(new Label("type", flow.getType().name()));
        item.add(new Label("currencyType", flow.getCurrencyType().name()));
        item.add(new Label("sum", String.valueOf(flow.getSum())));
        item.add(new Label("date", flow.getDate().toString()));
      }

    });
  }
}
