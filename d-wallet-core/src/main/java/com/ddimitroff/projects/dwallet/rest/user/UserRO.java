package com.ddimitroff.projects.dwallet.rest.user;

/*
 * {"username":"mykob.11@gmail.com", "hashPassword":"mykob.11", "defaultCurrency":"1"}
 */
public class UserRO {

	private String username;
	private String hashPassword;
	private int defaultCurrency;

	public UserRO() {
	}

	public UserRO(String username, String hashPassword, int defaultCurrency) {
		this.username = username;
		this.hashPassword = hashPassword;
		this.defaultCurrency = defaultCurrency;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public int getDefaultCurrency() {
		return defaultCurrency;
	}

	public void setDefaultCurrency(int defaultCurrency) {
		this.defaultCurrency = defaultCurrency;
	}

}
