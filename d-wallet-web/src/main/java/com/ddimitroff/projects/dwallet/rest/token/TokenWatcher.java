package com.ddimitroff.projects.dwallet.rest.token;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;

public class TokenWatcher {

	private UserDAOManager userManager;
	private UserDAO admin;

	public UserDAO getAdmin() {
		return admin;
	}

	public void setAdmin(UserDAO admin) {
		this.admin = admin;
	}

	public void init() throws Exception {
		System.out.println("initttttttttttttttttt");
		UserDAO dao = userManager.getUserByName(admin.getEmail());
		if (dao == null) {
			userManager.saveUser(admin, admin);
		}
	}

	public void destroy() {
		System.out.println("destroyyyyyyyyyyyyyyyy");
	}

	public UserDAOManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDAOManager userManager) {
		this.userManager = userManager;
	}

}
