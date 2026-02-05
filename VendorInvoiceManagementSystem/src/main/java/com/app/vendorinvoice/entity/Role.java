package com.app.vendorinvoice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "roles", uniqueConstraints = {
		@UniqueConstraint(name = "roles_role_name_key", columnNames = "role_name") })
@Data
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long roleId;

	@Column(name = "role_name", length = 50, nullable = false)
	private String roleName;

	@Column(name = "description", length = 100)
	private String description;

}
