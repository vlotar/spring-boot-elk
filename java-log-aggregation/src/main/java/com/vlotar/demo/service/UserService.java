package com.vlotar.demo.service;

import com.google.common.collect.Lists;
import com.vlotar.demo.dao.UserDAO;
import com.vlotar.demo.domain.User;
import com.vlotar.demo.exception.NotFoundException;
import com.vlotar.demo.exception.OperationNotAcceptableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @author vlotar
 */
@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public Collection<User> getAllUsers() {
		return Lists.newArrayList(this.userDAO.findAll());
	}

	public User getUser(final Long userId) {
		User user = this.userDAO.findOne(userId);
		if (user == null) {
			throw new NotFoundException(String.format("User %s not found", userId));
		}
		return user;
	}

	public Long createUser(final User user) {
		if (user.getId() != null) {
			throw new OperationNotAcceptableException(String.format("Cannot create user with Id: %s", user.getId()));
		}
		return this.userDAO.save(user).getId();
	}

	public void updateUser(final User user) {
		this.userDAO.save(user);
	}

	public void deleteUser(final Long userId) {
		User user = this.userDAO.findOne(userId);
		if (user == null) {
			throw new NotFoundException(String.format("User %s not found", userId));
		}
		this.userDAO.delete(userId);
	}
}
