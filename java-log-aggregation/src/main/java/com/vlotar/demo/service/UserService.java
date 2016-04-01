package com.vlotar.demo.service;

import com.google.common.collect.Lists;
import com.vlotar.demo.dao.UserDAO;
import com.vlotar.demo.domain.User;
import com.vlotar.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * Service layer is responsible for performing basic CRUD operations on {@link User} resource.
 *
 * @author vlotar
 */
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	/**
	 * Allows to retrieve all existing users from the database.
	 * Pagination is not implemented in order to keep it simple.
	 *
	 * @return the all existing {@link User} entities
	 */
	public Collection<User> getAllUsers() {
		return Lists.newArrayList(this.userDAO.findAll());
	}

	/**
	 * Allows to get a user by the given user identifier.
	 *
	 * @param userId the user id
	 * @return the {@link User}
	 */
	public User getUser(final Long userId) {
		return findUserOrThrowNotFoundException(userId);
	}

	/**
	 * Allows to create a new user and persist the resource in the database.
	 * Operation is transactional.
	 * Operation cannot be executed if client specifies user identifier.
	 *
	 * @param user the {@link User} resource
	 * @return newly created user identifier
	 */
	@Transactional
	public Long createUser(final User user) {
		//persist new user and return user identifier
		return this.userDAO.save(user).getId();
	}

	/**
	 * Allows to update specific user.
	 * Operation is transactional.
	 *
	 * @param user the {@link User} resource
	 */
	@Transactional
	public void updateUser(final User user) {
		//check that user exists
		findUserOrThrowNotFoundException(user.getId());
		//persist user
		this.userDAO.save(user);
	}

	/**
	 * Allows to delete a user by the given user identifier.
	 * Operation is transactional.
	 *
	 * @param userId user identifier
	 */
	@Transactional
	public void deleteUser(final Long userId) {
		//check that user exists
		findUserOrThrowNotFoundException(userId);
		//delete existing user
		this.userDAO.delete(userId);
	}

	private User findUserOrThrowNotFoundException(final Long userId) {
		User user = this.userDAO.findOne(userId);
		if (user == null) {
			throw new ResourceNotFoundException(String.format("User %s not found", userId));
		}
		return user;
	}

	/**
	 * Sets user dao.
	 *
	 * @param userDAO the user dao
	 */
	void setUserDAO(final UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}
