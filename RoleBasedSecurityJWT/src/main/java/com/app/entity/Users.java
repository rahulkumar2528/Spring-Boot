package com.app.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name ="user_name", length = 50, nullable = false, unique = true)
	private String userName;
	
	@Column(name = "password", length = 500, nullable = false )
	private String password;
	
	@Column(name ="status", nullable = false)
	private Integer status;
	
}
