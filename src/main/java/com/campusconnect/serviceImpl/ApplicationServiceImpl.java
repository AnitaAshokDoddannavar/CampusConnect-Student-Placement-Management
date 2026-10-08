package com.campusconnect.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.dto.request.ApplicationRequestDTO;
import com.campusconnect.dto.response.ApplicationResponseDTO;
import com.campusconnect.entity.Application;
import com.campusconnect.entity.JobDrive;
import com.campusconnect.entity.Student;
import com.campusconnect.exception.DuplicateApplicationException;
import com.campusconnect.exception.NotEligibleException;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.ApplicationMapper;
import com.campusconnect.repository.ApplicationRepository;
import com.campusconnect.repository.JobDriveRepository;
import com.campusconnect.repository.StudentRepository;
import com.campusconnect.service.ApplicationService;
import com.campusconnect.entity.ApplicationStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final StudentRepository studentRepository;
    private final JobDriveRepository jobDriveRepository;
    private final ApplicationMapper applicationMapper;

    @Transactional
    @Override
    public ApplicationResponseDTO createApplication(
            ApplicationRequestDTO applicationRequestDTO) {

        // 1. Find Student
        Student student = studentRepository
                .findById(applicationRequestDTO.getStudentId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Student not found with id: "
                        + applicationRequestDTO.getStudentId()));

        // 2. Find Job Drive
        JobDrive jobDrive = jobDriveRepository
                .findById(applicationRequestDTO.getJobDriveId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Job drive not found with id: "
                        + applicationRequestDTO.getJobDriveId()));

        // 3. Check eligibility
        if (student.getCgpa() < jobDrive.getMinimumCgpa()
                || !student.getDepartment()
                        .equalsIgnoreCase(
                                jobDrive.getEligibleDepartment())) {

            throw new NotEligibleException(
                    "Student is not eligible for this job drive");
        }

        // 4. Check duplicate application
        boolean alreadyApplied =
                applicationRepository
                        .existsByStudentStudentIdAndJobDriveJobDriveId(
                                applicationRequestDTO.getStudentId(),
                                applicationRequestDTO.getJobDriveId());

        if (alreadyApplied) {
            throw new DuplicateApplicationException(
                    "Student has already applied for this job drive");
        }

        // 5. Create Application
        Application application = new Application();

        application.setStudent(student);
        application.setJobDrive(jobDrive);

        // 6. Set application date
        application.setApplicationDate(LocalDate.now());

        // 7. Set initial status
        application.setStatus(ApplicationStatus.APPLIED);

        // 8. Save application
        Application savedApplication =
                applicationRepository.save(application);

        // 9. Convert Entity to Response DTO
        return applicationMapper.toResponseDTO(savedApplication);
    }

    @Override
    public ApplicationResponseDTO getApplicationById(
            Long applicationId) {

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Application not found with id: "
                        + applicationId));

        return applicationMapper.toResponseDTO(application);
    }

    @Override
    public List<ApplicationResponseDTO> getAllApplications() {

        return applicationRepository.findAll()
                .stream()
                .map(applicationMapper::toResponseDTO)
                .toList();
    }

    @Override
    public ApplicationResponseDTO updateApplicationStatus(
            Long applicationId,
            ApplicationStatus status) {

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Application not found with id: "
                        + applicationId));

        application.setStatus(status);

        Application updatedApplication =
                applicationRepository.save(application);

        return applicationMapper.toResponseDTO(updatedApplication);
    }
    
    @Override
    public Page<ApplicationResponseDTO> getApplicationsWithPagination(
            int page, int size) {

        Page<Application> applications =
                applicationRepository.findAll(
                        PageRequest.of(page, size));

        return applications.map(applicationMapper::toResponseDTO);
    }

    @Override
    public List<ApplicationResponseDTO> getApplicationsWithSorting(
            String sortBy, String direction) {

        Sort sort;

        if (direction.equalsIgnoreCase("desc")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        return applicationRepository.findAll(sort)
                .stream()
                .map(applicationMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteApplication(Long applicationId) {

        Application application = applicationRepository
                .findById(applicationId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Application not found with id: "
                        + applicationId));

        applicationRepository.delete(application);
    }

	
}