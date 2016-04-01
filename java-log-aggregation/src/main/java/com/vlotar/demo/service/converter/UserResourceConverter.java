package com.vlotar.demo.service.converter;

import com.vlotar.demo.domain.User;
import com.vlotar.demo.web.request.CreateUserRequest;
import com.vlotar.demo.web.request.UpdateUserRequest;
import com.vlotar.demo.web.response.UserResourceResponse;
import org.springframework.stereotype.Component;

/**
 * Helper class which is responsible for converting domain {@link User} entity to some DTO objects.
 * {@link UserResourceResponse}
 * {@link CreateUserRequest}
 * {@link UpdateUserRequest}
 *
 * @author vlotar
 */
@Component
public class UserResourceConverter {

	/**
	 * Converts {@link User} to {@link UserResourceResponse}
	 *
	 * @param user {@link User}
	 * @return {@link UserResourceResponse}
	 */
	public UserResourceResponse convert(final User user) {
		return new UserResourceResponse(user.getId(), user.getFirstName(), user.getLastName(), user.getCountry());
	}

	/**
	 * Converts {@link CreateUserRequest} to a domain {@link User}.
	 *
	 * @param request {@link CreateUserRequest}
	 * @return {@link User}
	 */
	public User convert(final CreateUserRequest request) {
		final User user = new User();
		convertCommonFields(request, user);
		return user;
	}

	/**
	 * Converts {@link UpdateUserRequest} to a domain {@link User}.
	 *
	 * @param request {@link UpdateUserRequest}
	 * @return {@link User}
	 */
	public User convert(final UpdateUserRequest request) {
		final User user = new User();
		user.setId(request.getId());
		convertCommonFields(request, user);
		return user;
	}

	private void convertCommonFields(final CreateUserRequest request, final User user) {
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setCountry(request.getCountry());
	}
}
