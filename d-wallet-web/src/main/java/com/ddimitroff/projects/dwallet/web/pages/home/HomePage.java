package com.ddimitroff.projects.dwallet.web.pages.home;

import java.util.List;

import org.apache.wicket.Page;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.ajax.markup.html.modal.ModalWindow;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.managers.impl.UserManagerImpl;
import com.ddimitroff.projects.dwallet.web.pages.cash.CashBalancePage;
import com.ddimitroff.projects.dwallet.web.pages.cash.CashFlowsPage;

/**
 * Class for 'Home' page of web administration application
 * 
 * @author Dimitar Dimitrov
 * 
 */
public class HomePage extends WebPage {

  /** Serialization field */
  private static final long serialVersionUID = 1L;

  /** Injected {@link UserManagerImpl} object by Spring */
  @SpringBean
  private UserManager userManager;

  /**
   * Constructor for creating new {@link HomePage} object
   * 
   * @param parameters
   *          - initial page parameters
   */
  public HomePage(final PageParameters parameters) {
    super(parameters);

    add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    List<User> allUsers = userManager.getAll(User.class);

    add(new ListView<User>("usersView", allUsers) {

      private static final long serialVersionUID = 1L;

      protected void populateItem(ListItem<User> item) {
        final User user = (User) item.getModelObject();
        item.add(new Label("id", String.valueOf(user.getId())));
        item.add(new Label("email", user.getEmail()));
        item.add(new Label("hashPassword", user.getHashPassword()));
        item.add(new Label("role", user.getRole().name()));
        item.add(new Label("defaultCurrency", null != user.getDefaultCurrency() ? user.getDefaultCurrency().name()
            : "N/A"));

        item.add(new AjaxLink<Void>("deleteUser") {

          private static final long serialVersionUID = 1L;

          @Override
          public void onClick(AjaxRequestTarget target) {
            userManager.delete(user);
            setResponsePage(HomePage.class);
          }

        });

        final ModalWindow cashBalanceModal = new ModalWindow("cashBalanceModal");
        item.add(cashBalanceModal);

        cashBalanceModal.setCookieName("cash-balance-modal");
        cashBalanceModal.setPageCreator(new ModalWindow.PageCreator() {

          private static final long serialVersionUID = 1L;

          public Page createPage() {
            return new CashBalancePage(user.getId());
          }

        });

        item.add(new AjaxLink<Void>("showCashBalanceModal") {

          private static final long serialVersionUID = 1L;

          @Override
          public void onClick(AjaxRequestTarget target) {
            cashBalanceModal.show(target);
          }

        });

        final ModalWindow cashFlowsModal = new ModalWindow("cashFlowsModal");
        item.add(cashFlowsModal);

        cashFlowsModal.setCookieName("cash-flows-modal");
        cashFlowsModal.setPageCreator(new ModalWindow.PageCreator() {

          private static final long serialVersionUID = 1L;

          public Page createPage() {
            return new CashFlowsPage(user);
          }

        });

        item.add(new AjaxLink<Void>("showCashFlowsModal") {

          private static final long serialVersionUID = 1L;

          @Override
          public void onClick(AjaxRequestTarget target) {
            cashFlowsModal.show(target);
          }

        });
      }

    });

  }

}
