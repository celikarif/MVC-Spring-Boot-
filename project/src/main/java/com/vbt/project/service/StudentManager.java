package com.vbt.project.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vbt.project.dto.Mapper;
import com.vbt.project.dto.requestDto.StudentRequestDto;
import com.vbt.project.dto.responseDto.StudentResponseDto;
import com.vbt.project.model.Department;
import com.vbt.project.model.Student;
import com.vbt.project.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentManager implements StudentService {

	private final StudentRepository studentRepository;
	private final DepartmentService departmentService;

	@Autowired
	public StudentManager(StudentRepository studentRepository, DepartmentService departmentService) {

		this.studentRepository = studentRepository;
		this.departmentService = departmentService;
	}
	
	@Transactional
	@Override
	public StudentResponseDto addStudent(StudentRequestDto studentRequestDto) {
		Student student = new Student();
		student.setName(studentRequestDto.getName());
		student.setBirthday(studentRequestDto.getBirthday());
		if (studentRequestDto.getDepartmentId() == 0) {
			throw new IllegalArgumentException("Student need a department");
		}
		Department department = departmentService.getDepartment(studentRequestDto.getDepartmentId());
		student.setDepartment(department);

		Student student1 = studentRepository.save(student);
		return Mapper.studentToStudentResponseDto(student1);
	}

	@Override
	public List<StudentResponseDto> getStudents() {
		List<Student> students = StreamSupport														
				.stream(studentRepository.findAll().spliterator(), false)
				.collect(Collectors.toList());
	
		return Mapper.studentToStudentResponseDtos(students);
	}
	

	@Override
	public StudentResponseDto getStudentById(int studentId) {
		Student student = getStudent(studentId);
		
	
		return Mapper.studentToStudentResponseDto(student);
	}

	@Override
	public Student getStudent(int studentId) {
		Student student = studentRepository.findById(studentId).orElseThrow(()->
						new IllegalArgumentException("can not find student with id:" + studentId));
		
		
		return student;
	}

	@Override
	public StudentResponseDto deleteStudent(int studentId) {
		Student student = getStudent(studentId);
		
		studentRepository.delete(student);
		return Mapper.studentToStudentResponseDto(student);
	}

	@Override
	public StudentResponseDto editStudent(int studentId, StudentRequestDto studentRequestDto) {
		Student studentToEdit = getStudent(studentId);
		studentToEdit.setName(studentRequestDto.getName());
		studentToEdit.setBirthday(studentRequestDto.getBirthday());
		if (studentRequestDto.getDepartmentId() != 0) {
			Department department = departmentService.getDepartment(studentRequestDto.getDepartmentId());
			studentToEdit.setDepartment(department);
			
		}
		studentRepository.save(studentToEdit);
		
		return Mapper.studentToStudentResponseDto(studentToEdit);
	}

	@Override
	public StudentResponseDto addDepartmentToStudent(int studentId, int departmentId) {
		Student student = getStudent(studentId);
		Department department = departmentService.getDepartment(departmentId);
		 if (Objects.nonNull(student.getDepartment())){
	            throw new IllegalArgumentException("Student already has a department");	
	        }
		 student.setDepartment(department);
		 department.addStudent(student);
		return Mapper.studentToStudentResponseDto(student);
	}

	@Override
	public StudentResponseDto removeDepartmentFromStudent(int studentId, int departmentId) {
		Student student = getStudent(studentId);
		Department department = departmentService.getDepartment(departmentId);
		if (!(Objects.nonNull(student.getDepartment()))){
            throw new IllegalArgumentException("Student does not have a department to delete");	
        }
		student.setDepartment(null);
		department.removeStudent(student);
		
		
		return Mapper.studentToStudentResponseDto(student);
	}

	@Override
	public List<StudentResponseDto> getStudentsByName(String name) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByName(name).spliterator(), false)
				.collect(Collectors.toList());
		
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByDepartmentId(int departmentId) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findBydepartmentId(departmentId).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByBirthday(LocalDate birthday) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByBirthday(birthday).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByNameAndDepartmentId(String name, int departmentId) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByNameAndDepartmentId(name, departmentId).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByNameAndBirthday(String name, LocalDate birthday) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByNameAndBirthday(name, birthday).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByBirtdayAndDepartmentId(LocalDate birthday,int departmentId) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByDepartmentIdAndBirthday(departmentId, birthday).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByNameAndBirthdayAndDepartmentId(String name, LocalDate birthday,int departmentId) {
		List<Student> students = StreamSupport
				.stream(studentRepository.findByNameAndBirthdayAndDepartmentId(name, birthday, departmentId).spliterator(), false)
				.collect(Collectors.toList());
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public Page<StudentResponseDto> getStudentsByPage(int page , int size) {
		PageRequest pageRequest = PageRequest.of(page, size);
		List<Student> students = StreamSupport														
				.stream(studentRepository.findAll(pageRequest).spliterator(), false)
				.collect(Collectors.toList());
		
				
		return Mapper.studentToStudentResponseDtosPage(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsWithSorting(LocalDate birthday) {
		List<Student> students = StreamSupport
				.stream(	studentRepository.findAll(Sort.by(Sort.Direction.ASC,"birthday")).spliterator(), false)
				.collect(Collectors.toList());
						
		return Mapper.studentToStudentResponseDtos(students);
	}

	@Override
	public List<StudentResponseDto> getStudentsByNameLike(String n) {
		List<Student> students = StreamSupport														
				.stream(studentRepository.findByNameLike(n).spliterator(), false)
				.collect(Collectors.toList());
	
	return Mapper.studentToStudentResponseDtos(students);
		
	}

}
