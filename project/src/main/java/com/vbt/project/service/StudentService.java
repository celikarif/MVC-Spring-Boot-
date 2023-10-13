package com.vbt.project.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.vbt.project.dto.requestDto.StudentRequestDto;
import com.vbt.project.dto.responseDto.StudentResponseDto;
import com.vbt.project.model.Student;

@Service
public interface StudentService {

	public StudentResponseDto addStudent(StudentRequestDto studentRequestDto);

	public List<StudentResponseDto> getStudents();
	
	public List<StudentResponseDto> getStudentsWithSorting(LocalDate birthday);
	
	public Page<StudentResponseDto> getStudentsByPage(int page , int size);

	public StudentResponseDto getStudentById(int studentId);

	public Student getStudent(int studentId);

	public StudentResponseDto deleteStudent(int studentId);

	public StudentResponseDto editStudent(int studentId, StudentRequestDto studentRequestDto);

	public StudentResponseDto addDepartmentToStudent(int studentId, int departmentId);

	public StudentResponseDto removeDepartmentFromStudent(int studentId, int departmentId);

	public List<StudentResponseDto> getStudentsByName(String name);

	public List<StudentResponseDto> getStudentsByDepartmentId(int departmentId);

	public List<StudentResponseDto> getStudentsByBirthday(LocalDate birthday);

	public List<StudentResponseDto> getStudentsByNameAndDepartmentId(String name, int departmentId);

	public List<StudentResponseDto> getStudentsByNameAndBirthday(String name, LocalDate birthday);

	public List<StudentResponseDto> getStudentsByBirtdayAndDepartmentId(LocalDate birthday ,int departmentId);

	public List<StudentResponseDto> getStudentsByNameAndBirthdayAndDepartmentId(String name, LocalDate birthday,int departmentId);
	
	public List<StudentResponseDto> getStudentsByNameLike(String name);

}
