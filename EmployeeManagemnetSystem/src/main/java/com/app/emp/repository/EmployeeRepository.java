package com.app.emp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.emp.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmpUserName(String username);
	
	Optional<Employee> findByEmpId(Integer empId);
	
	boolean existsByEmpId(Integer empId);
	
	void deleteByEmpId(Integer empId);
	
}
