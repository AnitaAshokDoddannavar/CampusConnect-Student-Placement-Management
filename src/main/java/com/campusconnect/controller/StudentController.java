package com.campusconnect.controller;

import java.util.List;

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

import com.campusconnect.dto.request.StudentRequestDTO;
import com.campusconnect.dto.response.StudentResponseDTO;
import com.campusconnect.service.StudentService;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO response =
                studentService.createStudent(studentRequestDTO);

        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable Long studentId) {

        StudentResponseDTO response =
                studentService.getStudentById(studentId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {

        List<StudentResponseDTO> students =
                studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    @PutMapping("/{studentId}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentRequestDTO studentRequestDTO) {

        StudentResponseDTO response =
                studentService.updateStudent(
                        studentId,
                        studentRequestDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<Void> deleteStudent(
            @PathVariable Long studentId) {

        studentService.deleteStudent(studentId);

        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/pagination")
    public ResponseEntity<Page<StudentResponseDTO>> getStudentsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        Page<StudentResponseDTO> students =
                studentService.getStudentsWithPagination(page, size);

        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/sorting")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsWithSorting(
            @RequestParam String sortBy,
            @RequestParam String direction) {

        List<StudentResponseDTO> students =
                studentService.getStudentsWithSorting(sortBy, direction);

        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/department/{department}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByDepartment(
            @PathVariable String department) {

        List<StudentResponseDTO> students =
                studentService.getStudentsByDepartment(department);

        return ResponseEntity.ok(students);
    }
    
    @GetMapping("/cgpa/{cgpa}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByMinimumCgpa(
            @PathVariable Double cgpa) {

        List<StudentResponseDTO> students =
                studentService.getStudentsByMinimumCgpa(cgpa);

        return ResponseEntity.ok(students);
    }
}