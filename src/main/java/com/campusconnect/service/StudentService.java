package com.campusconnect.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.campusconnect.dto.request.StudentRequestDTO;
import com.campusconnect.dto.response.StudentResponseDTO;

public interface StudentService {

    StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO);

    StudentResponseDTO getStudentById(Long studentId);

    List<StudentResponseDTO> getAllStudents();

    StudentResponseDTO updateStudent(
            Long studentId,
            StudentRequestDTO studentRequestDTO);

    void deleteStudent(Long studentId);

    Page<StudentResponseDTO> getStudentsWithPagination(int page, int size);
    
    List<StudentResponseDTO> getStudentsWithSorting(
            String sortBy,
            String direction);
    
    List<StudentResponseDTO> getStudentsByDepartment(
            String department);
    
    List<StudentResponseDTO> getStudentsByMinimumCgpa(
            Double cgpa);
}