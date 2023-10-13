package com.vbt.project.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vbt.project.dto.requestDto.StudentRequestDto;
import com.vbt.project.dto.responseDto.StudentResponseDto;
import com.vbt.project.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;
	
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	
	}

	@PostMapping("/add")
	public ResponseEntity<StudentResponseDto> addStudent(@RequestBody final StudentRequestDto studentRequestDto) {
		StudentResponseDto studentResponseDto = studentService.addStudent(studentRequestDto);
		return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	}

	@GetMapping("/getall")
	public ResponseEntity<List<StudentResponseDto>> getStudents() {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudents();
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
	@GetMapping("/getallbypage") 
	public ResponseEntity<Page<StudentResponseDto>> getStudentsByPage(@RequestParam int page , @RequestParam int size) {
		Page<StudentResponseDto> studentResponseDtos = studentService.getStudentsByPage(page, size);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
	@GetMapping("/getallsort")
	public ResponseEntity<List<StudentResponseDto>> getStudentsWithSorting() {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsWithSorting(null);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	@GetMapping("/getall/like/{st}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByLike(@PathVariable String st) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByNameLike(st);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
		
	@GetMapping("/get/{id}")
	public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable  int id) {
		
		StudentResponseDto studentResponseDto = studentService.getStudentById(id);
		return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	}

	@GetMapping("/get/name/{name}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByName(@PathVariable final String name) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByName(name);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}

	@GetMapping("/get/departmentid/{departmentid}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartmentId(@PathVariable final int departmentid) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByDepartmentId(departmentid);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}

	@GetMapping("/get/birthday/{birthday}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByBirthday(@PathVariable final LocalDate birthday) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByBirthday(birthday);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}

	@GetMapping("/get/name/{name}/departmentid/{departmentid}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByNameAndDepartmentId(@PathVariable final String name,@PathVariable final int departmentid) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByNameAndDepartmentId(name,
				departmentid);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/name/{name}/birthday/{birthday}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByNameAndBirthday(@PathVariable final String name,@PathVariable final LocalDate birthday) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByNameAndBirthday(name, birthday);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	@GetMapping("/get/birthday/{birthday}/departmentid/{departmentid}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByDepartmentIdAndBirthday(@PathVariable final LocalDate birthday,@PathVariable final int departmentid) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByBirtdayAndDepartmentId(birthday, departmentid);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
	@GetMapping("/get/name/{name}/birthday/{birthday}/departmentid/{departmentid}")
	public ResponseEntity<List<StudentResponseDto>> getStudentsByNameAndBirthdayAndDepartmentId(@PathVariable final String name,@PathVariable final LocalDate birthday,@PathVariable final int departmentid) {
		List<StudentResponseDto> studentResponseDtos = studentService.getStudentsByNameAndBirthdayAndDepartmentId(name, birthday, departmentid);
		return new ResponseEntity<>(studentResponseDtos, HttpStatus.OK);
	}
	
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<StudentResponseDto> deleteStudent(@PathVariable final int id) {
		 StudentResponseDto studentResponseDto = studentService.deleteStudent(id);
	        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	    }
	 
	 @PutMapping("/edit/{id}")
	 public ResponseEntity<StudentResponseDto> editStudent(@RequestBody final  StudentRequestDto studentRequestDto, @PathVariable final int id) {
		 StudentResponseDto  studentResponseDto = studentService.editStudent(id, studentRequestDto);
	        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	    }
	 
	 @PostMapping("/addDepartment/{departmentid}/to/{studentid}")
	    public ResponseEntity<StudentResponseDto> addDepartment(@PathVariable final int departmentid,
	                                                       @PathVariable final int studentid) {
		 StudentResponseDto studentResponseDto =studentService.addDepartmentToStudent(studentid, departmentid);
	        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	    }
	 

	    @PutMapping("/removeDepartment/{departmentid}/from/{studentid}")
	    public ResponseEntity<StudentResponseDto> removeDepartment(@PathVariable final int departmentid,
	                                                          @PathVariable final int studentid) {
			 StudentResponseDto studentResponseDto =studentService.removeDepartmentFromStudent(studentid, departmentid);
	        return new ResponseEntity<>(studentResponseDto, HttpStatus.OK);
	    }
	    
		
	 

}
