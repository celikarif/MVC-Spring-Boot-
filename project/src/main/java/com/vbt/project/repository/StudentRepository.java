package com.vbt.project.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.vbt.project.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> , PagingAndSortingRepository<Student, Integer>{
	
	
	@Query(value="SELECT * FROM \"student\" WHERE name LIKE %:name%", nativeQuery = true)
	List<Student> findByNameLike(@Param("name") String name);
	
	List<Student> findByName(String name);

	List<Student> findBydepartmentId(int departmentId);

	List<Student> findByBirthday(LocalDate birthday);

	List<Student> findByNameAndDepartmentId(String name, int departmentId);

	List<Student> findByNameAndBirthday(String name, LocalDate birthday);

	List<Student> findByDepartmentIdAndBirthday(int departmentId, LocalDate birthday);

	List<Student> findByNameAndBirthdayAndDepartmentId(String name,  LocalDate birthday , int departmentId);


}
