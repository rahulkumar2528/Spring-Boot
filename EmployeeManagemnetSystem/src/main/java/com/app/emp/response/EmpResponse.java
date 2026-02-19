package com.app.emp.response;

import lombok.Data;

@Data
public class EmpResponse {
	private Integer empId;
	private String empName;
	private String empEmail;
	private String departmenName;
	private String status;	
}
