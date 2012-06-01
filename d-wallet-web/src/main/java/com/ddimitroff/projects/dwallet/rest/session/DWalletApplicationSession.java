package com.ddimitroff.projects.dwallet.rest.session;

import java.util.List;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.managers.UserManager;

public class DWalletApplicationSession {

	private static final Logger logger = Logger.getLogger(DWalletApplicationSession.class);

	private UserManager userManager;
	private List<User> adminUsers;

	public List<User> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(List<User> adminUsers) {
		this.adminUsers = adminUsers;
	}

	public void init() throws Exception {
		long start = System.nanoTime();
		validateAdminUsers(adminUsers);
		logger.info("Initializing 'd-wallet' application session finished in " + (System.nanoTime() - start) / 1000000
				+ " ms.");
	}

	private void validateAdminUsers(List<User> adminUsers) throws Exception {
		for (int i = 0; i < adminUsers.size(); i++) {
			User currentAdmin = adminUsers.get(i);

			User entity = userManager.getUserByEmail(currentAdmin.getEmail());
			if (null != entity) {
				continue;
			} else {
				userManager.save(currentAdmin);
			}
		}
	}

	public void destroy() {
		logger.info("Shutting down 'd-wallet' application session...");
	}

	public UserManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserManager userManager) {
		this.userManager = userManager;
	}

}
