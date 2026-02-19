package com.app.emp.service.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.emp.entity.Department;
import com.app.emp.entity.Employee;
import com.app.emp.exception.BadRequestException;
import com.app.emp.exception.ResourceNotFoundException;
import com.app.emp.repository.DepartmentRepository;
import com.app.emp.repository.EmployeeRepository;
import com.app.emp.request.EmpRequest;
import com.app.emp.response.EmpResponse;
import com.app.emp.service.EmpService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

	private final EmployeeRepository empRepo;
	private final DepartmentRepository deptRepo;
	private final PasswordEncoder passwordEncoder;

	public EmpServiceImpl(EmployeeRepository empRepo, DepartmentRepository deptRepo, PasswordEncoder passwordEncoder) {
		this.empRepo = empRepo;
		this.deptRepo = deptRepo;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public EmpResponse createEmp(EmpRequest empRequest) {

		if (empRequest == null || empRequest.getDepartmenId() == null) {
			throw new BadRequestException("Required Parameter Missing");
		}

		Department dept = deptRepo.findByDepId(empRequest.getDepartmenId())
				.orElseThrow(() -> new ResourceNotFoundException("Department Not Exixt for Department Id"));

		Employee emp = new Employee();
		emp.setEmpName(empRequest.getEmpName());
		emp.setEmpEmail(empRequest.getEmpEmail());
		emp.setEmpUserName(empRequest.getEmpUserName());
		emp.setEmpPassword(passwordEncoder.encode(empRequest.getEmpPassword()));
		emp.setDepartment(dept);
		return mapToResponse(empRepo.save(emp));
	}

	@Override
	public EmpResponse getEmpByEmpId(Integer empId){
		Employee emp = empRepo.findByEmpId(empId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
		return mapToResponse(emp);
	}

	@Override
	public List<EmpResponse> getAllEmp() {

		List<Employee> empList = empRepo.findAll();
		List<EmpResponse> list = empList.stream().map(emp -> {
			return mapToResponse(emp);
			}).toList();
		
		return  list;
	}

	@Override
	public EmpResponse updateEmp(Integer empId, EmpRequest empRequest) {
		Employee emp = empRepo.findByEmpId(empId).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));
        Department dept = deptRepo.findById(empRequest.getDepartmenId()).orElseThrow(() -> new RuntimeException("Dept not found"));
        
        emp.setEmpName(empRequest.getEmpName());
		emp.setEmpEmail(empRequest.getEmpEmail());
		emp.setDepartment(dept);
		Employee dbEmp = empRepo.save(emp);
        return mapToResponse(dbEmp);
	}

	@Override
	public String deleteEmp(Integer empId) {
		if (!empRepo.existsByEmpId(empId)) {
            throw new ResourceNotFoundException("Employee not found with id: " + empId);
        }
		empRepo.deleteByEmpId(empId);
		return "Employee deleted successfully";
	}

	private EmpResponse mapToResponse(Employee emp) {
		EmpResponse empResponse = new EmpResponse();
		empResponse.setEmpId(emp.getEmpId());
		empResponse.setEmpName(emp.getEmpName());
		empResponse.setEmpEmail(emp.getEmpEmail());
		String depName = emp.getDepartment().getDepName();
		empResponse.setDepartmenName(depName);
		empResponse.setStatus(emp.getStatus().getDescription());
		return empResponse;
	}
}
