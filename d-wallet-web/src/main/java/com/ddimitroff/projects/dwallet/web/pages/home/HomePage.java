package com.ddimitroff.projects.dwallet.web.pages.home;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.UserManager;

/**
 * Class for 'Home' page
 * 
 * @author ddimitrov
 * 
 */
public class HomePage extends WebPage {
  private static final long serialVersionUID = 1L;

  @SpringBean
  private UserManager userManager;

  public HomePage(final PageParameters parameters) {
    super(parameters);

    add(new Label("version", getApplication().getFrameworkSettings().getVersion()));

    // TODO Add your page's components here
    List<User> allUsers = userManager.getAll(User.class);
    System.out.println("ALL users " + allUsers.size());

    add(new ListView("usersView", allUsers) {
      protected void populateItem(ListItem item) {
        User user = (User) item.getModelObject();
        item.add(new Label("id", String.valueOf(user.getId())));
        item.add(new Label("email", user.getEmail()));
        item.add(new Label("hashPassword", user.getHashPassword()));
        item.add(new Label("role", user.getRole().name()));
        item.add(new Label("defaultCurrency", null != user.getDefaultCurrency() ? user.getDefaultCurrency().name()
            : "N/A"));
        item.add(new Label("startupBalance", String.valueOf(user.getStartupBalance())));
      }
    });

  }
}
