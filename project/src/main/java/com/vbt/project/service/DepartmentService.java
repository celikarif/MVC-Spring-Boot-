package com.vbt.project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vbt.project.dto.requestDto.DepartmentRequestDto;
import com.vbt.project.dto.responseDto.DepartmentResponseDto;
import com.vbt.project.model.Department;

@Service
public interface DepartmentService {

	public DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto);

	public Department getDepartment(int departmentId);

	public DepartmentResponseDto getDepartmentById(int departmentId);

	public List<DepartmentResponseDto> getDepartments();

	public DepartmentResponseDto deleteDepartment(int departmentId);

	public DepartmentResponseDto editDepartment(int departmentId, DepartmentRequestDto departmentRequestDto);
}
