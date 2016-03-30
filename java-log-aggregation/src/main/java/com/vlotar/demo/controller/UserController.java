package com.vlotar.demo.controller;

import com.google.gson.Gson;
import com.vlotar.demo.domain.User;
import com.vlotar.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author vlotar
 */
@RestController
@RequestMapping("/users")
//TODO: inject SWAGGER
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private static final String STATUS_SUCCESS = "{\"status\":\"success\"}";

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUser(@PathVariable final Long userId) {
		LOGGER.debug("Trying to retrieve User by ID: " + userId);
		return toJson(this.userService.getUser(userId));
	}


	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAllUsers() {
		LOGGER.debug("Trying to retrieve all users");
		return toJson(this.userService.getAllUsers());
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createUser(@Valid @RequestBody User user) {
		LOGGER.debug("Trying to create a user: " + user.toString());
		Long userId = this.userService.createUser(user);
		return "{\"id\":" + userId + "}";
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public String updateUser(@PathVariable final Long userId, @Valid @RequestBody User user) {
		LOGGER.debug("Trying to update a user: " + user.toString());
		user.setId(userId);
		this.userService.updateUser(user);
		return STATUS_SUCCESS;
	}

	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public String deleteUser(@PathVariable final Long userId) {
		LOGGER.debug("Trying to delete a user: " + userId);
		this.userService.deleteUser(userId);
		return STATUS_SUCCESS;
	}

	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	/**
	 * Sets user service.
	 *
	 * @param userService the user service
	 */
	void setUserService(final UserService userService) {
		this.userService = userService;
	}
}
