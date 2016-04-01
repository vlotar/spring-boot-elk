package com.vlotar.demo.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.io.Serializable;

/**
 * Represents successful response when there is nothing to return
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@SuppressWarnings("unused")
@Getter
public class SuccessResponse implements Serializable {

	private static final long serialVersionUID = -355194637398843627L;

	@ApiModelProperty(value = "Status of the response", allowableValues = "success", required = true)
	private final String status = "success";
}
