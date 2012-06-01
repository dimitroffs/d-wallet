package com.ddimitroff.projects.dwallet.managers.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ddimitroff.projects.dwallet.db.dao.UserDAO;
import com.ddimitroff.projects.dwallet.db.entities.User;
import com.ddimitroff.projects.dwallet.enums.CashFlowCurrencyType;
import com.ddimitroff.projects.dwallet.enums.UserRole;
import com.ddimitroff.projects.dwallet.managers.UserManager;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

@Service("userManager")
public class UserManagerImpl extends BaseManagerImpl<User> implements UserManager {

	@Autowired
	private UserDAO userDao;

	@Override
	public void postConstruct() {
		baseDao = userDao;
	}

	public User getUserByCredentials(String email, String password) {
		return userDao.getUserByCredentials(email, password);
	}

	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	public UserRO convert(User entity) {
		UserRO ro = new UserRO(entity.getEmail(), entity.getHashPassword(), entity.getDefaultCurrency()
				.getIntCurrencyCode(), entity.getStartupBalance());
		return ro;
	}

	/*
	 * Used for registration of new user
	 */
	public User convert(UserRO ro) {
		User entity = new User(ro.getUsername(), UserRole.USER, CashFlowCurrencyType.getCurrencyType(ro
				.getDefaultCurrency()), ro.getStartupBalance());
		entity.setHashPassword(ro.getHashPassword());
		return entity;
	}

	/*
	 * Used for login of user
	 */
	public User getConvertedUser(UserRO ro) {
		User entity = getUserByCredentials(ro.getUsername(), ro.getHashPassword());
		if (null != entity) {
			return entity;
		}

		return null;
	}

}
