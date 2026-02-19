package com.app.emp.service;

import java.util.List;

import com.app.emp.request.EmpRequest;
import com.app.emp.response.EmpResponse;

public interface EmpService {

	EmpResponse createEmp(EmpRequest empRequest);
	
	EmpResponse getEmpByEmpId(Integer empId);
	
	List<EmpResponse> getAllEmp();
	
	EmpResponse updateEmp(Integer empId, EmpRequest empRequest);
	
	String deleteEmp(Integer emppId);
}
