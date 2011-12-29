package com.ddimitroff.projects.dwallet.db;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
		@NamedQuery(name = UserDAO.GET_USER_BY_CREDENTIALS, query = "SELECT user FROM UserDAO user WHERE user.email = :email AND user.hashPassword = :password"),
		@NamedQuery(name = UserDAO.GET_USER_BY_CREDENTIALS_USER, query = "SELECT user FROM UserDAO user WHERE user.userName = :userName AND user.hashPassword = :password"),
		@NamedQuery(name = UserDAO.GET_USER_BY_EMAIL, query = "SELECT user FROM UserDAO user WHERE user.email = :email"),
		@NamedQuery(name = UserDAO.GET_USER_BY_USERNAME, query = "SELECT user FROM UserDAO user WHERE user.userName = :userName") })
public class UserDAO implements Comparable<UserDAO>, Serializable {

	private static final long serialVersionUID = 1L;

	public static final String GET_USER_BY_EMAIL = "User.getUserByEmail";
	public static final String GET_USER_BY_CREDENTIALS = "User.getUserByCredentials";
	public static final String GET_USER_BY_CREDENTIALS_USER = "User.getUserByCredentialsUser";
	public static final String GET_ALL_USERS = "User.getAllUsers";
	public static final String GET_USER_BY_USERNAME = "User.getUserByUserName";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(unique = true, length = 64)
	private String email;

	@Column(length = 64)
	private String hashPassword;

	@Column(length = 32)
	@Enumerated(EnumType.STRING)
	private UserDAORoles role;

	@Column(unique = true, length = 16)
	private String userName;

	@Column
	private Date birthDate;

	@Column(length = 1)
	private String gender;

	@Column
	private Date lastLoginDate;

	@Column
	private Date createDate;

	@Column
	private boolean userActive;

	@Column
	private boolean emailVisible;

	@Column
	private boolean userNameVisible;

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public boolean isUserActive() {
		return userActive;
	}

	public void setUserActive(boolean userActive) {
		this.userActive = userActive;
	}

	public boolean isEmailVisible() {
		return emailVisible;
	}

	public void setEmailVisible(boolean emailVisible) {
		this.emailVisible = emailVisible;
	}

	public boolean isUserNameVisible() {
		return userNameVisible;
	}

	public void setUserNameVisible(boolean userNameVisible) {
		this.userNameVisible = userNameVisible;
	}

	public static final UserDAO SYSTEM_ADMINISTRATOR = new UserDAO("SYSADMIN", UserDAORoles.ADMIN);

	public UserDAO() {
	}

	public UserDAO(String email, UserDAORoles role) {
		if (email == null)
			throw new IllegalArgumentException("User name should be specified!");
		if (role == null)
			throw new IllegalArgumentException("User role should be specified!");

		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	public void setHashPassword(String hashPassword) {
		this.hashPassword = hashPassword;
	}

	public UserDAORoles getRole() {
		return role;
	}

	public void setRole(UserDAORoles role) {
		this.role = role;
	}

	@Override
	public int compareTo(UserDAO user) {
		return this.getEmail().compareTo(user.getEmail());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());

		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDAO other = (UserDAO) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (role == null) {
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return email + " [" + role + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
