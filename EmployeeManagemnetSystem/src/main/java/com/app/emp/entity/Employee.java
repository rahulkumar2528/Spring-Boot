package com.app.emp.entity;

import com.app.emp.config.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "employee")
@Data
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="emp_id")
	private Integer empId;

	@Column(name = "emp_name", length = 150)
	private String empName;
	
	@Column(name = "emp_email", length = 150)
	private String empEmail;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
	
	@Column(name = "emp_username", length = 100)
	private String empUserName;
	
	@Column(name = "emp_password", length = 300)
	private String empPassword;
	
	@Column(name = "status")
	private Status status = Status.ACTIVE;
	
}
