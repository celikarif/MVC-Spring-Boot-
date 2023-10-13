package com.vbt.project.dto.responseDto;

import java.util.List;

import lombok.Data;

@Data
public class DepartmentResponseDto {
	private int id;
	private String name;
	private List<String> studentNames;

}
