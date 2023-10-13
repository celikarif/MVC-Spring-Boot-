package com.vbt.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vbt.project.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
}
