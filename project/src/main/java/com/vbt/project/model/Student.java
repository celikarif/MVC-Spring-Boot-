package com.vbt.project.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "student")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String name;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    @JoinColumn(name = "department_id"  , nullable = true)     
	private Department department;
	
	private LocalDate birthday;

	public Student(String name, Department department, LocalDate birthday) {
		this.name = name;
		this.department = department;
		this.birthday = birthday;
	}
	
	
}
