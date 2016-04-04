package com.vlotar.demo.web.controller;

import com.vlotar.demo.domain.User;
import com.vlotar.demo.service.UserService;
import com.vlotar.demo.service.converter.UserResourceConverter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author vlotar
 */
public class UserControllerTest {

	private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8"));

	private MockMvc mockMvc;

	private UserService userService;

	@Before
	public void setUp() throws Exception {
		UserController userController = new UserController();
		ErrorHandler errorHandler = new ErrorHandler();

		this.userService = Mockito.mock(UserService.class);
		userController.setUserService(this.userService);

		final UserResourceConverter converter = new UserResourceConverter();
		userController.setConverter(converter);

		this.mockMvc = MockMvcBuilders.standaloneSetup(userController, errorHandler).build();
	}

	@Test
	public void getUser() throws Exception {
		User user = new User();
		user.setCountry("UA");
		user.setFirstName("Mickey");
		user.setLastName("Mouse");
		Mockito.when(this.userService.getUser(1L)).thenReturn(user);
		this.mockMvc.perform(get("/users/1")).andExpect(status().isOk())
				.andExpect(content().string("{\"firstName\":\"Mickey\",\"lastName\":\"Mouse\",\"country\":\"UA\"}"));
	}

	@Test
	public void getAllUsers() throws Exception {
		Mockito.when(this.userService.getAllUsers()).thenReturn(new ArrayList<>());
		this.mockMvc.perform(get("/users")).andExpect(status().isOk()).andExpect(content().string("[]"));
	}

	@Test
	public void createUser() throws Exception {
		Mockito.when(this.userService.createUser(Mockito.any())).thenReturn(1L);
		this.mockMvc.perform(post("/users").contentType(APPLICATION_JSON_UTF8).content("{\"firstName\":\"Mickey\", \"lastName\":\"Mouse\", \"country\":\"UA\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"id\":1}"));
	}

	@Test
	public void updateUser() throws Exception {
		this.userService.updateUser(Mockito.any());
		this.mockMvc.perform(
				put("/users/1").contentType(APPLICATION_JSON_UTF8).content("{\"firstName\":\"Mickey\", \"lastName\":\"Mouse\", \"country\":\"UA\"}"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"status\":\"success\"}"));
	}

	@Test
	public void deleteUser() throws Exception {
		this.userService.deleteUser(1L);
		this.mockMvc.perform(delete("/users/1"))
				.andExpect(status().isOk())
				.andExpect(content().string("{\"status\":\"success\"}"));
	}
}