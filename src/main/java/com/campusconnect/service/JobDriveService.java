package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.request.JobDriveRequestDTO;
import com.campusconnect.dto.response.JobDriveResponseDTO;

public interface JobDriveService {

    JobDriveResponseDTO createJobDrive(
            JobDriveRequestDTO jobDriveRequestDTO);

    JobDriveResponseDTO getJobDriveById(Long jobDriveId);

    List<JobDriveResponseDTO> getAllJobDrives();

    JobDriveResponseDTO updateJobDrive(
            Long jobDriveId,
            JobDriveRequestDTO jobDriveRequestDTO);

    void deleteJobDrive(Long jobDriveId);
}