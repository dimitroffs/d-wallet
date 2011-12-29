package com.ddimitroff.projects.dwallet.rest.session;

import com.ddimitroff.projects.dwallet.db.UserDAOManager;
import com.ddimitroff.projects.dwallet.spring.BeanHelper;

public class DWalletApplicationSession {

	private UserDAOManager userManager = BeanHelper.getBean(UserDAOManager.class, "userManager");

	public void init() {
		System.out.println("initttttttttttttttttt");
	}

	public void destroy() {
		System.out.println("destroyyyyyyyyyyyyyyyy");
	}

}
