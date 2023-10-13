package com.vbt.project.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vbt.project.dto.Mapper;
import com.vbt.project.dto.requestDto.DepartmentRequestDto;
import com.vbt.project.dto.responseDto.DepartmentResponseDto;
import com.vbt.project.model.Department;
import com.vbt.project.repository.DepartmentRepository;

@Service
public class DepartmentManager implements DepartmentService {

	private final DepartmentRepository departmentRepository;

	@Autowired
	public DepartmentManager(DepartmentRepository departmentRepository) {
		this.departmentRepository = departmentRepository;
	}

	@Override
	public DepartmentResponseDto addDepartment(DepartmentRequestDto departmentRequestDto) {

		Department department = new Department();
		department.setName(departmentRequestDto.getName());
		departmentRepository.save(department);

		return Mapper.departmentToDepartmentResponseDto(department);
	}

	@Override
	public Department getDepartment(int departmentId) {
		return departmentRepository.findById(departmentId)
				.orElseThrow(() -> new IllegalArgumentException("could not find department with id: " + departmentId));

	}

	@Override
	public DepartmentResponseDto getDepartmentById(int departmentId) {
		Department department = getDepartment(departmentId);
		
		
		return Mapper.departmentToDepartmentResponseDto(department);
	}

	@Override
	public List<DepartmentResponseDto> getDepartments() {
		List<Department> departments = StreamSupport
                .stream(departmentRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return Mapper.departmentToDepartmentResponseDtos(departments);
	}

	@Override
	public DepartmentResponseDto deleteDepartment(int departmentId) {
		Department department = getDepartment(departmentId);
		departmentRepository.delete(department);
		return Mapper.departmentToDepartmentResponseDto(department);
	}

	@Override
	public DepartmentResponseDto editDepartment(int departmentId, DepartmentRequestDto departmentRequestDto) {
		Department departmentToEdit = getDepartment(departmentId);
		departmentToEdit.setName(departmentRequestDto.getName());
		
		return Mapper.departmentToDepartmentResponseDto(departmentToEdit);
	}

}
