package com.vbt.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vbt.project.dto.requestDto.DepartmentRequestDto;
import com.vbt.project.dto.responseDto.DepartmentResponseDto;
import com.vbt.project.service.DepartmentService;

@RestController
@RequestMapping("/department")
public class DepartmentController {

	private final DepartmentService departmentService;

	@Autowired
	public DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@PostMapping("/add")
	public ResponseEntity<DepartmentResponseDto> addDepartment(@RequestBody final DepartmentRequestDto departmentRequestDto) {
		DepartmentResponseDto departmentResponseDto = departmentService.addDepartment(departmentRequestDto);

		return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);

	}
	
	 @GetMapping("/get/{id}")
	 public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable final int id) {
		 DepartmentResponseDto departmentResponseDto = departmentService.getDepartmentById(id);
	        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	    }
	 
	 @GetMapping("/getall")
	 public ResponseEntity<List<DepartmentResponseDto>> getDepartments() {
	        List<DepartmentResponseDto> departmentResponseDtos = departmentService.getDepartments();
	        return new ResponseEntity<>(departmentResponseDtos, HttpStatus.OK);
	    }
	 
	 @DeleteMapping("/delete/{id}")
	 		public ResponseEntity<DepartmentResponseDto> deleteDepartment(@PathVariable final int id) {
		 DepartmentResponseDto departmentResponseDto = departmentService.deleteDepartment(id);
	        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	    }
	 
	 @PostMapping("/edit/{id}")
	 public ResponseEntity<DepartmentResponseDto> editDepartment( @RequestBody final DepartmentRequestDto departmentRequestDto,@PathVariable final int id) {
		
		 DepartmentResponseDto departmentResponseDto = departmentService.editDepartment(id, departmentRequestDto);
	        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
	    }
}
