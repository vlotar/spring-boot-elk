package com.vlotar.demo.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents response body when {@link com.vlotar.demo.domain.User} resource has to be returned
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@AllArgsConstructor
@Getter
public class UserResourceResponse implements Serializable {

	private static final long serialVersionUID = -8761235292937715094L;

	@ApiModelProperty(value = "Unique user identifier", required = true)
	private Long id;

	@ApiModelProperty(value = "User's first name", required = true)
	private String firstName;

	@ApiModelProperty(value = "User's last name", required = true)
	private String lastName;

	@ApiModelProperty(value = "Country where user's living", required = true)
	private String country;
}
