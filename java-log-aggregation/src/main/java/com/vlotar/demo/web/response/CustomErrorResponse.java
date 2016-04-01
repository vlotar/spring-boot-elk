package com.vlotar.demo.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * {@link CustomErrorResponse} will be returned in case of custom error occurrence
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class CustomErrorResponse implements Serializable {

	private static final long serialVersionUID = -7755563009111273632L;

	@ApiModelProperty(value = "Custom Error code", required = true)
	private String errorCode;

	@ApiModelProperty(value = "Custom Error message")
	private String errorMessage;

}
