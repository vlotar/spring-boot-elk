package com.vlotar.demo.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * {@link User} resource persisted in the database.
 *
 * @author vlotar
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class User implements Serializable {

	private static final long serialVersionUID = -1674204092853306884L;

	@ApiModelProperty(value = "Unique user identifier")
	@Id
	@GeneratedValue
	private Long id;

	@ApiModelProperty(value = "User's first name", required = true)
	@Column(name = "first_name")
	@NotBlank
	private String firstName;

	@ApiModelProperty(value = "User's last name", required = true)
	@Column(name = "last_name")
	@NotBlank
	private String lastName;

	@ApiModelProperty(value = "Country where user's living", required = true)
	@Column(name = "country")
	@NotBlank
	private String country;

}
