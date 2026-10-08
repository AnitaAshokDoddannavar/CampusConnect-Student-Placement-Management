package com.campusconnect.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.campusconnect.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE LOWER(s.department) = LOWER(:department)")
    List<Student> findStudentsByDepartment(
            @Param("department") String department);
    
    
    @Query(
    	    value = "SELECT * FROM student WHERE cgpa >= :cgpa",
    	    nativeQuery = true
    	)
    	List<Student> findStudentsByMinimumCgpa(
    	        @Param("cgpa") Double cgpa);
}