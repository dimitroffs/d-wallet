package com.ddimitroff.projects.dwallet.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ddimitroff.projects.dwallet.db.UserDAO;
import com.ddimitroff.projects.dwallet.db.UserDAOManager;
import com.ddimitroff.projects.dwallet.rest.user.UserRO;

/**
 * This is an example REST style MVC controller. It serves as an endpoint for
 * retrieving Product Objects.
 */
@Controller
public class UsersRestService {

	@Autowired
	private UserDAOManager userManager;

	/**
	 * This method returns a specific Product. The URI to request a Product is
	 * specified in the @RequestMapping.
	 * 
	 * @param productId
	 *          the identifier of the requested product
	 * @return a Product
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/users/get/{productId}")
	@ResponseBody
	public UserRO getUserById(@PathVariable String productId) {
		UserDAO dao = userManager.getUserByName(productId);
		UserRO ro = userManager.convert(dao);

		return ro;
	}

	// Content-Type: application/json needed
	@RequestMapping(method = RequestMethod.PUT, value = "/users/register")
	@ResponseStatus(value = HttpStatus.OK)
	public void registerUser(@RequestBody UserRO ro) {
		UserDAO daoToRegister = userManager.convert(ro);
		try {
			userManager.saveUser(daoToRegister);
		} catch (Exception e) {
			// TODO log error
			e.printStackTrace();
		}
	}

}