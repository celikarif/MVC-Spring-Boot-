package com.vbt.project.dto.responseDto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class StudentResponseDto {

	private int id;
	private String name;
	private String departmentName;
	private LocalDate birthday;

}
