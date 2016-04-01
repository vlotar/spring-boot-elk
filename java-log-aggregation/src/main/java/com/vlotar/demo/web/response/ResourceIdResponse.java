package com.vlotar.demo.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

/**
 * Specific Response which is used for the cases when only resource identifier has to be present in the response.
 * Necessary for proper Swagger documentation.
 *
 * @author vlotar
 */
@SuppressWarnings("unused")
@AllArgsConstructor
@Getter
public class ResourceIdResponse implements Serializable {

	private static final long serialVersionUID = -7692197744600484558L;

	@ApiModelProperty(value = "Resource identifier", required = true)
	private Long id;
}
