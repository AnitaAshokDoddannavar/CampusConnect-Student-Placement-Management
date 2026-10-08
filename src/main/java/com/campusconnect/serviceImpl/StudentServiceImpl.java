package com.campusconnect.serviceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.campusconnect.dto.request.StudentRequestDTO;
import com.campusconnect.dto.response.StudentResponseDTO;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.StudentMapper;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.service.StudentService;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger =
            LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;

    private final StudentMapper studentMapper;

    public StudentServiceImpl(
            StudentRepository studentRepository,
            StudentMapper studentMapper) {

        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    // =========================
    // CREATE STUDENT
    // =========================

    @Override
    public StudentResponseDTO createStudent(
            StudentRequestDTO studentRequestDTO) {

        logger.info("Creating new student with email: {}",
                studentRequestDTO.getEmail());

        Student student =
                studentMapper.toEntity(studentRequestDTO);

        Student savedStudent =
                studentRepository.save(student);

        logger.info("Student created successfully with id: {}",
                savedStudent.getStudentId());

        return studentMapper.toResponseDTO(savedStudent);
    }

    // =========================
    // GET STUDENT BY ID
    // =========================

    @Cacheable(value = "students", key = "#studentId")
    @Override
    public StudentResponseDTO getStudentById(Long studentId) {

        logger.info("Fetching student with id: {}", studentId);

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.warn(
                            "Student not found with id: {}",
                            studentId);

                    return new ResourceNotFoundException(
                            "Student not found with id: "
                                    + studentId);
                });

        return studentMapper.toResponseDTO(student);
    }

    // =========================
    // GET ALL STUDENTS
    // =========================

    @Override
    public List<StudentResponseDTO> getAllStudents() {

        logger.info("Fetching all students");

        List<Student> students =
                studentRepository.findAll();

        return students.stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    // =========================
    // UPDATE STUDENT
    // =========================

    @CachePut(value = "students", key = "#studentId")
    @Override
    public StudentResponseDTO updateStudent(
            Long studentId,
            StudentRequestDTO studentRequestDTO) {

        logger.info("Updating student with id: {}",
                studentId);

        Student existingStudent =
                studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.warn(
                            "Student not found with id: {}",
                            studentId);

                    return new ResourceNotFoundException(
                            "Student not found with id: "
                                    + studentId);
                });

        existingStudent.setName(
                studentRequestDTO.getName());

        existingStudent.setEmail(
                studentRequestDTO.getEmail());

        existingStudent.setPhone(
                studentRequestDTO.getPhone());

        existingStudent.setCgpa(
                studentRequestDTO.getCgpa());

        existingStudent.setDepartment(
                studentRequestDTO.getDepartment());

        existingStudent.setGraduationYear(
                studentRequestDTO.getGraduationYear());

        Student updatedStudent =
                studentRepository.save(existingStudent);

        logger.info(
                "Student updated successfully with id: {}",
                updatedStudent.getStudentId());

        return studentMapper.toResponseDTO(
                updatedStudent);
    }

    // =========================
    // DELETE STUDENT
    // =========================

    @CacheEvict(value = "students", key = "#studentId")
    @Override
    public void deleteStudent(Long studentId) {

        logger.info("Deleting student with id: {}",
                studentId);

        Student existingStudent =
                studentRepository.findById(studentId)
                .orElseThrow(() -> {

                    logger.warn(
                            "Student not found with id: {}",
                            studentId);

                    return new ResourceNotFoundException(
                            "Student not found with id: "
                                    + studentId);
                });

        studentRepository.delete(existingStudent);

        logger.info(
                "Student deleted successfully with id: {}",
                studentId);
    }

    // =========================
    // PAGINATION
    // =========================

    @Override
    public Page<StudentResponseDTO> getStudentsWithPagination(
            int page,
            int size) {

        logger.info(
                "Fetching students with pagination - page: {}, size: {}",
                page,
                size);

        Pageable pageable =
                PageRequest.of(page, size);

        Page<Student> students =
                studentRepository.findAll(pageable);

        return students.map(
                studentMapper::toResponseDTO);
    }

    // =========================
    // SORTING
    // =========================

    @Override
    public List<StudentResponseDTO> getStudentsWithSorting(
            String sortBy,
            String direction) {

        logger.info(
                "Fetching students sorted by: {}, direction: {}",
                sortBy,
                direction);

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {

            sort = Sort.by(sortBy).descending();

        } else {

            sort = Sort.by(sortBy).ascending();
        }

        List<Student> students =
                studentRepository.findAll(sort);

        return students.stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    // =========================
    // JPQL QUERY
    // =========================

    @Override
    public List<StudentResponseDTO> getStudentsByDepartment(
            String department) {

        logger.info(
                "Fetching students by department: {}",
                department);

        List<Student> students =
                studentRepository.findStudentsByDepartment(
                        department);

        return students.stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }

    // =========================
    // NATIVE QUERY
    // =========================

    @Override
    public List<StudentResponseDTO> getStudentsByMinimumCgpa(
            Double cgpa) {

        logger.info(
                "Fetching students with minimum CGPA: {}",
                cgpa);

        List<Student> students =
                studentRepository.findStudentsByMinimumCgpa(
                        cgpa);

        return students.stream()
                .map(studentMapper::toResponseDTO)
                .toList();
    }
}