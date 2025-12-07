package com.app.entity;

import java.util.List;

import com.app.validator.groups.OnCreate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {	
	
	@NotBlank(message = "Employee name cannot be blank", groups = OnCreate.class)
    private String empName; 
	
	@Email(regexp = ".*@gmail\\.com")
    private String empEmailId;   
    private double empSalary;   
    private List<String> empQualifications;
	
}
