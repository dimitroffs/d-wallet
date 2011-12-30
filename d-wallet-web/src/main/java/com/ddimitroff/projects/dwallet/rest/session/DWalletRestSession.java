package com.ddimitroff.projects.dwallet.rest.session;

import java.util.Date;

import com.ddimitroff.projects.dwallet.db.UserDAO;

public class DWalletRestSession {
//TODO NOT NEEDED DUE TO USING TokenWatcher
	private UserDAO currentUser;
	private Date loginDate;

	public DWalletRestSession(UserDAO currentUser) {
		this.currentUser = currentUser;
		this.loginDate = new Date();
	}

	public UserDAO getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserDAO currentUser) {
		this.currentUser = currentUser;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

}
