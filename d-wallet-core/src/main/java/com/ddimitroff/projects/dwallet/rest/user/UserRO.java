package com.ddimitroff.projects.dwallet.rest.user;

/*
 * {"username":"mykob.11@gmail.com", "hashPassword":"mykob.11"}
 */
public class UserRO {

	private String username;
	private String hashPassword;

	public UserRO() {
	}

	public UserRO(String username, String hashPassword) {
		this.username = username;
		this.hashPassword = hashPassword;
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

}
