package com.vbt.project.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.vbt.project.dto.responseDto.DepartmentResponseDto;
import com.vbt.project.dto.responseDto.StudentResponseDto;
import com.vbt.project.model.Department;
import com.vbt.project.model.Student;

public class Mapper {

	public static StudentResponseDto studentToStudentResponseDto(Student student) {
		StudentResponseDto studentResponseDto = new StudentResponseDto();
		studentResponseDto.setId(student.getId());
		studentResponseDto.setName(student.getName());
		studentResponseDto.setBirthday(student.getBirthday());
		studentResponseDto.setDepartmentName(student.getDepartment().getName());

		return studentResponseDto;

	}

	public static List<StudentResponseDto> studentToStudentResponseDtos(List<Student> students) {
		List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
		for (Student student : students) {
			studentResponseDtos.add(studentToStudentResponseDto(student));
		}

		return studentResponseDtos;
	}
	
	public static Page<StudentResponseDto> studentToStudentResponseDtosPage(List<Student> students) {
		List<StudentResponseDto> studentResponseDtos = new ArrayList<>();
		for (Student student : students) {
			studentResponseDtos.add(studentToStudentResponseDto(student));
		}

		return new PageImpl<>(studentResponseDtos);
	}
	
	public static DepartmentResponseDto departmentToDepartmentResponseDto(Department department) {
		DepartmentResponseDto departmentResponseDto = new DepartmentResponseDto();
		departmentResponseDto.setId(department.getId());
		departmentResponseDto.setName(department.getName());
		List<String> names = new ArrayList<>();
		List<Student> students = department.getStudents();
		for (Student student : students) {
			names.add(student.getName());
		}
		departmentResponseDto.setStudentNames(names);

		return departmentResponseDto;
	}

	public static List<DepartmentResponseDto> departmentToDepartmentResponseDtos(List<Department> departments) {
		List<DepartmentResponseDto> departmentResponseDtos = new ArrayList<>();
		for (Department department : departments) {
			departmentResponseDtos.add(departmentToDepartmentResponseDto(department));
		}

		return departmentResponseDtos;
	}

}
