package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entity.Employee;
import com.app.validator.groups.OnCreate;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

	 @PostMapping("/addEmp")
	public ResponseEntity<String> addEmployee(@Validated(OnCreate.class) @RequestBody Employee emp) {
		return new ResponseEntity<String>(
				"Employee details are valid and added successfully! \n" +
						"Name: " + emp.getEmpName() + "\n" +
		                "Email: " + emp.getEmpEmailId() + "\n" +
		                "Salary: " + emp.getEmpSalary()+ "\n" +
		                "Qualifications: " + emp.getEmpQualifications(),
		                HttpStatus.OK
		                );
	}
}
