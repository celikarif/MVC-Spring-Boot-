package com.vbt.project.dto.requestDto;

import java.time.LocalDate;

import lombok.Data;


@Data
public class StudentRequestDto {
	
	private String name;
	private int departmentId;  
	private LocalDate birthday;

}
