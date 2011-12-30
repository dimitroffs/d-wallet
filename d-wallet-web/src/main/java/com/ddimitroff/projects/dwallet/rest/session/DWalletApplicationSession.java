package com.ddimitroff.projects.dwallet.rest.session;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;
import com.ddimitroff.projects.dwallet.rest.token.Token;
import com.ddimitroff.projects.dwallet.rest.token.TokenGenerator;

public class DWalletApplicationSession {

	private UserDAOManager userManager;
	private UserDAO admin;
	private TokenGenerator tokenGenerator;

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
		
		Token t = tokenGenerator.generate(dao.getEmail(), dao.getHashPassword());
		System.out.println(t);
	}

	public TokenGenerator getTokenGenerator() {
		return tokenGenerator;
	}

	public void setTokenGenerator(TokenGenerator tokenGenerator) {
		this.tokenGenerator = tokenGenerator;
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
