package com.app.emp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.emp.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

	Optional<Department> findByDepId(Integer deptId);
}
