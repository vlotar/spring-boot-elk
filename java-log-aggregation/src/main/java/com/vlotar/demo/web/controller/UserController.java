package com.vlotar.demo.web.controller;

import com.google.gson.Gson;
import com.vlotar.demo.service.UserService;
import com.vlotar.demo.service.converter.UserResourceConverter;
import com.vlotar.demo.web.request.UserResourceRequest;
import com.vlotar.demo.web.response.ResourceIdResponse;
import com.vlotar.demo.web.response.SuccessResponse;
import com.vlotar.demo.web.response.UserResourceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
import java.util.stream.Collectors;

/**
 * @author vlotar
 */
@Api(value = "/users", description = "API manages 'users' allowing to perform basic CRUD operations")
@RestController
@RequestMapping("/users") class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private UserResourceConverter converter;

	@ApiOperation(
			value = "Retrieve 'request' by Id",
			notes = "Allows to retrieve existing 'request' resource by its identifier",
			response = UserResourceResponse.class
	)
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getUser(@ApiParam(value = "Unique 'request' identifier") @PathVariable final Long userId) {
		LOGGER.debug("Trying to retrieve User by ID: " + userId);
		return toJson(this.converter.convert(this.userService.getUser(userId)));
	}

	@ApiOperation(
			value = "Retrieve all 'users'",
			notes = "Allows to retrieve all existing 'users'",
			response = UserResourceResponse.class,
			responseContainer = "Set"
	)
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String getAllUsers() {
		LOGGER.debug("Trying to retrieve all users");
		return toJson(
				this.userService.getAllUsers().stream()
						.map(user -> this.converter.convert(user)).collect(Collectors.toSet()));
	}

	@ApiOperation(
			value = "Create new 'request'",
			notes = "Allows to create new 'request'",
			response = ResourceIdResponse.class
	)
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String createUser(@Valid @RequestBody UserResourceRequest request) {
		LOGGER.debug("Trying to create a user: " + request.toString());
		Long userId = this.userService.createUser(this.converter.convert(request));
		return toJson(new ResourceIdResponse(userId));
	}

	@ApiOperation(
			value = "Update existing 'request'",
			notes = "Allows to update existing 'request'",
			response = SuccessResponse.class
	)
	@RequestMapping(value = "/{userId}", method = RequestMethod.PUT, produces = "application/json")
	@ResponseBody
	public String updateUser(@ApiParam(value = "Unique 'request' identifier") @PathVariable final Long userId,
									 @Valid @RequestBody UserResourceRequest request) {
		LOGGER.debug("Trying to update a user: " + request.toString());
		this.userService.updateUser(this.converter.convert(request, userId));
		return toJson(new SuccessResponse());
	}

	@ApiOperation(
			value = "Delete existing 'request'",
			notes = "Allows to delete existing 'request'",
			response = SuccessResponse.class
	)
	@RequestMapping(value = "/{userId}", method = RequestMethod.DELETE, produces = "application/json")
	@ResponseBody
	public String deleteUser(@ApiParam(value = "Unique 'request' identifier") @PathVariable final Long userId) {
		LOGGER.debug("Trying to delete a user: " + userId);
		this.userService.deleteUser(userId);
		return toJson(new SuccessResponse());
	}

	private static String toJson(Object object) {
		return new Gson().toJson(object);
	}

	/**
	 * Sets request service.
	 *
	 * @param userService the request service
	 */
	void setUserService(final UserService userService) {
		this.userService = userService;
	}

	/**
	 * Sets converter.
	 *
	 * @param converter the converter
	 */
	void setConverter(final UserResourceConverter converter) {
		this.converter = converter;
	}
}
