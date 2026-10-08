package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.request.JobDriveRequestDTO;
import com.campusconnect.dto.response.JobDriveResponseDTO;
import com.campusconnect.service.JobDriveService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/job-drives")
@RequiredArgsConstructor
public class JobDriveController {

    private final JobDriveService jobDriveService;

    @PostMapping
    public ResponseEntity<JobDriveResponseDTO> createJobDrive(
            @Valid @RequestBody JobDriveRequestDTO jobDriveRequestDTO) {

        JobDriveResponseDTO response =
                jobDriveService.createJobDrive(jobDriveRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{jobDriveId}")
    public ResponseEntity<JobDriveResponseDTO> getJobDriveById(
            @PathVariable Long jobDriveId) {

        JobDriveResponseDTO response =
                jobDriveService.getJobDriveById(jobDriveId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<JobDriveResponseDTO>> getAllJobDrives() {

        List<JobDriveResponseDTO> response =
                jobDriveService.getAllJobDrives();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{jobDriveId}")
    public ResponseEntity<JobDriveResponseDTO> updateJobDrive(
            @PathVariable Long jobDriveId,
            @Valid @RequestBody JobDriveRequestDTO jobDriveRequestDTO) {

        JobDriveResponseDTO response =
                jobDriveService.updateJobDrive(
                        jobDriveId, jobDriveRequestDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{jobDriveId}")
    public ResponseEntity<Void> deleteJobDrive(
            @PathVariable Long jobDriveId) {

        jobDriveService.deleteJobDrive(jobDriveId);

        return ResponseEntity.noContent().build();
    }
}