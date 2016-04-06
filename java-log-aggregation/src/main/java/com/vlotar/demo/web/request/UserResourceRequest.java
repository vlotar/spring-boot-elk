package com.vlotar.demo.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Represents request body for Create User operation.
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@Getter
@Setter
@ToString
public class UserResourceRequest implements Serializable {

	private static final long serialVersionUID = 2657944775357946081L;

	@ApiModelProperty(value = "User's first name", required = true)
	@NotBlank
	private String firstName;

	@ApiModelProperty(value = "User's last name", required = true)
	@NotBlank
	private String lastName;

	@ApiModelProperty(value = "Country where user's living", required = true)
	@NotBlank
	private String country;

}
