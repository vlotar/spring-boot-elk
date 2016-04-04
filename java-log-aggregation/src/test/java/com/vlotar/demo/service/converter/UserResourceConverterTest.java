package com.vlotar.demo.service.converter;

import com.vlotar.demo.domain.User;
import com.vlotar.demo.web.request.UserResourceRequest;
import com.vlotar.demo.web.response.UserResourceResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author vlotar
 */
public class UserResourceConverterTest {

	private UserResourceConverter converter;

	@Before
	public void setUp() throws Exception {
		this.converter = new UserResourceConverter();
	}

	@Test
	public void convertUserToUserResourceResponse() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setFirstName("Mickey");
		user.setLastName("Mouse");
		user.setCountry("UA");
		UserResourceResponse response = this.converter.convert(user);
		Assert.assertEquals(1, response.getId().longValue());
		Assert.assertEquals("Mickey", response.getFirstName());
		Assert.assertEquals("Mouse", response.getLastName());
		Assert.assertEquals("UA", response.getCountry());
	}

	@Test
	public void convertCreateUserRequestToUser() throws Exception {
		UserResourceRequest request = new UserResourceRequest();
		request.setFirstName("Mickey");
		request.setLastName("Mouse");
		request.setCountry("UA");

		User user = this.converter.convert(request);
		Assert.assertEquals("Mickey", user.getFirstName());
		Assert.assertEquals("Mouse", user.getLastName());
		Assert.assertEquals("UA", user.getCountry());
		Assert.assertNull(user.getId());
	}

	@Test
	public void convertUpdateUserRequestToUser() throws Exception {
		UserResourceRequest request = new UserResourceRequest();
		request.setFirstName("Mickey");
		request.setLastName("Mouse");
		request.setCountry("UA");

		User user = this.converter.convert(request, 1L);
		Assert.assertEquals("Mickey", user.getFirstName());
		Assert.assertEquals("Mouse", user.getLastName());
		Assert.assertEquals("UA", user.getCountry());
		Assert.assertEquals(1, user.getId().longValue());
	}
}