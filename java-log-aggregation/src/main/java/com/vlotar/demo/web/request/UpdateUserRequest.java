package com.vlotar.demo.web.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Represents request body for Update User operation.
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@Getter
@Setter
public class UpdateUserRequest extends CreateUserRequest implements Serializable {

	private static final long serialVersionUID = 2144148483113697775L;

	@ApiModelProperty(value = "Unique user identifier", required = true)
	@NotNull
	private Long id;
}
