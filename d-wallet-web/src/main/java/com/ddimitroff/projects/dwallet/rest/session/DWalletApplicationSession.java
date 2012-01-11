package com.ddimitroff.projects.dwallet.rest.session;

import java.util.List;

import org.apache.log4j.Logger;

import com.ddimitroff.projects.dwallet.db.user.UserDAO;
import com.ddimitroff.projects.dwallet.db.user.UserDAOManager;

public class DWalletApplicationSession {

	private static final Logger logger = Logger.getLogger(DWalletApplicationSession.class);

	private UserDAOManager userManager;
	private List<UserDAO> adminUsers;

	public List<UserDAO> getAdminUsers() {
		return adminUsers;
	}

	public void setAdminUsers(List<UserDAO> adminUsers) {
		this.adminUsers = adminUsers;
	}

	public void init() throws Exception {
		long start = System.nanoTime();
		validateAdminUsers(adminUsers);
		// TODO implement and start Quartz scheduler
		logger.info("Initializing 'd-wallet' application session finished in " + (System.nanoTime() - start) / 1000000 + " ms.");
	}

	private void validateAdminUsers(List<UserDAO> adminUsers) throws Exception {
		for (int i = 0; i < adminUsers.size(); i++) {
			UserDAO currentAdmin = adminUsers.get(i);

			UserDAO dao = userManager.getUserByName(currentAdmin.getEmail());
			if (null != dao) {
				continue;
			} else {
				userManager.saveUser(currentAdmin);
			}
		}
	}

	public void destroy() {
		// TODO implement and shutdown Quartz scheduler
		logger.info("Shutting down 'd-wallet' application session...");
	}

	public UserDAOManager getUserManager() {
		return userManager;
	}

	public void setUserManager(UserDAOManager userManager) {
		this.userManager = userManager;
	}

}
