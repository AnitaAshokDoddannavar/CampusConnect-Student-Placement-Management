package com.campusconnect.servicetest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.campusconnect.dto.request.StudentRequestDTO;
import com.campusconnect.dto.response.StudentResponseDTO;
import com.campusconnect.entity.Student;
import com.campusconnect.mapper.StudentMapper;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.serviceImpl.StudentServiceImpl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.campusconnect.exception.ResourceNotFoundException;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void getStudentById_WhenStudentExists_ShouldReturnStudent() {

        Student student = new Student();
        student.setStudentId(1L);
        student.setName("Anita Doddannavar");

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setName("Anita Doddannavar");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentMapper.toResponseDTO(student))
                .thenReturn(responseDTO);

        StudentResponseDTO result = studentService.getStudentById(1L);
                
        assertNotNull(result);
    }
    
    @Test
    void getStudentById_WhenStudentDoesNotExist_ShouldThrowException() {

        when(studentRepository.findById(99L))
                .thenReturn(Optional.empty());

        assertThrows(
                ResourceNotFoundException.class,
                () -> studentService.getStudentById(99L)
        );
    }
    
    @Test
    void createStudent_ShouldReturnCreatedStudent() {

        StudentRequestDTO requestDTO = new StudentRequestDTO();
        requestDTO.setName("Rahul");
        requestDTO.setEmail("rahul@gmail.com");

        Student student = new Student();
        student.setStudentId(2L);
        student.setName("Rahul");

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setName("Rahul");

        when(studentMapper.toEntity(requestDTO))
                .thenReturn(student);

        when(studentRepository.save(student))
                .thenReturn(student);

        when(studentMapper.toResponseDTO(student))
                .thenReturn(responseDTO);

        StudentResponseDTO result = studentService.createStudent(requestDTO);
                
        assertNotNull(result);
    }
    
    @Test
    void updateStudent_ShouldReturnUpdatedStudent() {

        StudentRequestDTO requestDTO = new StudentRequestDTO();
        requestDTO.setName("Rahul Updated");
        requestDTO.setEmail("rahulupdated@gmail.com");

        Student student = new Student();
        student.setStudentId(1L);
        student.setName("Rahul");

        StudentResponseDTO responseDTO = new StudentResponseDTO();
        responseDTO.setName("Rahul Updated");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        when(studentRepository.save(student))
                .thenReturn(student);

        when(studentMapper.toResponseDTO(student))
                .thenReturn(responseDTO);

        StudentResponseDTO result = studentService.updateStudent(1L, requestDTO);
                
        assertNotNull(result);
    }
    
    @Test
    void deleteStudent_ShouldDeleteStudent() {

        Student student = new Student();
        student.setStudentId(1L);
        student.setName("Rahul");

        when(studentRepository.findById(1L))
                .thenReturn(Optional.of(student));

        studentService.deleteStudent(1L);

        verify(studentRepository).delete(student);
    }
}