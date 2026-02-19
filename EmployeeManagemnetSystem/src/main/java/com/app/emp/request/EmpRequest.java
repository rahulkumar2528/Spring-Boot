package com.app.emp.request;

import lombok.Data;

@Data
public class EmpRequest {
	private String empName;
	private String empEmail;
	private Integer departmenId;
	private String status;
	private String empUserName;
	private String empPassword;
}
