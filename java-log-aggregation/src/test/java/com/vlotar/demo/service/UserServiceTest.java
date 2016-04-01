package com.vlotar.demo.service;

import com.vlotar.demo.dao.UserDAO;
import com.vlotar.demo.domain.User;
import com.vlotar.demo.exception.ResourceNotFoundException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;

import java.util.Collection;
import java.util.Collections;

/**
 * @author vlotar
 */
public class UserServiceTest {

	private UserService userService;

	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
		this.userService = new UserService();
		this.userDAO = Mockito.mock(UserDAO.class);
		this.userService.setUserDAO(this.userDAO);
	}

	@Test
	public void getAllUsers() throws Exception {
		User user = new User();
		Mockito.when(this.userDAO.findAll()).thenReturn(Collections.singleton(user));
		Collection<User> users = this.userService.getAllUsers();
		Assert.assertEquals(1, users.size());
		Assert.assertEquals(user, users.iterator().next());
	}

	@Test
	public void getUser() throws Exception {
		User user = new User();
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(user);
		Assert.assertEquals(user, this.userService.getUser(1L));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getUser_notFound() throws Exception {
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(null);
		this.userService.getUser(1L);
	}

	@Test
	public void createUser() throws Exception {
		User user = new User();
		Mockito.when(this.userDAO.save(user)).thenAnswer((Answer<User>) invocationOnMock -> {
			User user1 = (User) invocationOnMock.getArguments()[0];
			user1.setId(1L);
			return user1;
		});

		Assert.assertEquals(1, this.userService.createUser(user).longValue());
	}

	@Test
	public void updateUser() throws Exception {
		User user = new User();
		user.setId(1L);
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(user);
		this.userService.updateUser(user);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void updateUser_notFound() throws Exception {
		User user = new User();
		user.setId(1L);
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(null);
		this.userService.updateUser(user);
	}

	@Test
	public void deleteUser() throws Exception {
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(new User());
		this.userService.deleteUser(1L);
	}

	@Test(expected = ResourceNotFoundException.class)
	public void deleteUser_notFound() throws Exception {
		Mockito.when(this.userDAO.findOne(1L)).thenReturn(null);
		this.userService.deleteUser(1L);
	}
}