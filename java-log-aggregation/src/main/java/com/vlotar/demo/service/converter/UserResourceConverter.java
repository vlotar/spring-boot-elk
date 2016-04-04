package com.vlotar.demo.service.converter;

import com.vlotar.demo.domain.User;
import com.vlotar.demo.web.request.UserResourceRequest;
import com.vlotar.demo.web.response.UserResourceResponse;
import org.springframework.stereotype.Component;

/**
 * Helper class which is responsible for converting domain {@link User} entity to some DTO objects.
 * {@link UserResourceResponse}
 * {@link UserResourceRequest}
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
	 * Converts {@link UserResourceRequest} to a domain {@link User}.
	 *
	 * @param request {@link UserResourceRequest}
	 * @return {@link User}
	 */
	public User convert(final UserResourceRequest request) {
		final User user = new User();
		convertCommonFields(request, user);
		return user;
	}

	/**
	 * Converts {@link UserResourceRequest} to a domain {@link User}.
	 *
	 * @param request {@link UserResourceRequest}
	 * @return {@link User}
	 */
	public User convert(final UserResourceRequest request, final Long userId) {
		final User user = new User();
		user.setId(userId);
		convertCommonFields(request, user);
		return user;
	}

	private void convertCommonFields(final UserResourceRequest request, final User user) {
		user.setFirstName(request.getFirstName());
		user.setLastName(request.getLastName());
		user.setCountry(request.getCountry());
	}
}
