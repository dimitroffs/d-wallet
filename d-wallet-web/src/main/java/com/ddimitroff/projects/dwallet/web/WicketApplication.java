package com.ddimitroff.projects.dwallet.web;

import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.ddimitroff.projects.dwallet.web.pages.home.HomePage;

/**
 * Wicket class for web application.
 * 
 * @author ddimitrov
 * 
 */
public class WicketApplication extends WebApplication {
  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  @Override
  public Class<HomePage> getHomePage() {
    return HomePage.class;
  }

  /**
   * @see org.apache.wicket.Application#init()
   */
  @Override
  public void init() {
    super.init();

    // using Wicket Spring component injector...
    getComponentInstantiationListeners().add(new SpringComponentInjector(this));

    // add your configuration here
    mountPage("home", HomePage.class);
  }
}
