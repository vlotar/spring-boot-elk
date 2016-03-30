package com.vlotar.demo.domain;

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

	@Id
	@GeneratedValue
	private Long id;

	@Column(name = "first_name")
	@NotBlank
	private String firstName;

	@Column(name = "last_name")
	@NotBlank
	private String lastName;

	@Column(name = "country")
	@NotBlank
	private String country;

}
