package com.campusconnect.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.request.JobDriveRequestDTO;
import com.campusconnect.dto.response.JobDriveResponseDTO;
import com.campusconnect.entity.Company;
import com.campusconnect.entity.JobDrive;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.JobDriveMapper;
import com.campusconnect.repository.CompanyRepository;
import com.campusconnect.repository.JobDriveRepository;
import com.campusconnect.service.JobDriveService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobDriveServiceImpl implements JobDriveService {

    private final JobDriveRepository jobDriveRepository;
    private final CompanyRepository companyRepository;
    private final JobDriveMapper jobDriveMapper;

    @Override
    public JobDriveResponseDTO createJobDrive(
            JobDriveRequestDTO jobDriveRequestDTO) {

        Company company = companyRepository
                .findById(jobDriveRequestDTO.getCompanyId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Company not found with id: "
                        + jobDriveRequestDTO.getCompanyId()));

        JobDrive jobDrive =
                jobDriveMapper.toEntity(jobDriveRequestDTO);

        jobDrive.setCompany(company);

        JobDrive savedJobDrive =
                jobDriveRepository.save(jobDrive);

        return jobDriveMapper.toResponseDTO(savedJobDrive);
    }

    @Override
    public JobDriveResponseDTO getJobDriveById(Long jobDriveId) {

        JobDrive jobDrive = jobDriveRepository
                .findById(jobDriveId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Job drive not found with id: "
                        + jobDriveId));

        return jobDriveMapper.toResponseDTO(jobDrive);
    }

    @Override
    public List<JobDriveResponseDTO> getAllJobDrives() {

        return jobDriveRepository.findAll()
                .stream()
                .map(jobDriveMapper::toResponseDTO)
                .toList();
    }

    @Override
    public JobDriveResponseDTO updateJobDrive(
            Long jobDriveId,
            JobDriveRequestDTO jobDriveRequestDTO) {

        JobDrive jobDrive = jobDriveRepository
                .findById(jobDriveId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Job drive not found with id: "
                        + jobDriveId));

        Company company = companyRepository
                .findById(jobDriveRequestDTO.getCompanyId())
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Company not found with id: "
                        + jobDriveRequestDTO.getCompanyId()));

        jobDriveMapper.updateJobDriveFromDto(
                jobDriveRequestDTO, jobDrive);

        jobDrive.setCompany(company);

        JobDrive updatedJobDrive =
                jobDriveRepository.save(jobDrive);

        return jobDriveMapper.toResponseDTO(updatedJobDrive);
    }

    @Override
    public void deleteJobDrive(Long jobDriveId) {

        JobDrive jobDrive = jobDriveRepository
                .findById(jobDriveId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Job drive not found with id: "
                        + jobDriveId));

        jobDriveRepository.delete(jobDrive);
    }
}