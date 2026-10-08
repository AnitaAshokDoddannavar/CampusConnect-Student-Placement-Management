package com.campusconnect.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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

import com.campusconnect.dto.request.ApplicationRequestDTO;
import com.campusconnect.dto.response.ApplicationResponseDTO;
import com.campusconnect.service.ApplicationService;

import com.campusconnect.entity.ApplicationStatus;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping
    public ResponseEntity<ApplicationResponseDTO> createApplication(
            @Valid @RequestBody ApplicationRequestDTO applicationRequestDTO) {

        ApplicationResponseDTO response =
                applicationService.createApplication(applicationRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{applicationId}")
    public ResponseEntity<ApplicationResponseDTO> getApplicationById(
            @PathVariable Long applicationId) {

        ApplicationResponseDTO response =
                applicationService.getApplicationById(applicationId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<ApplicationResponseDTO>> getAllApplications() {

        List<ApplicationResponseDTO> response =
                applicationService.getAllApplications();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{applicationId}/status")
    public ResponseEntity<ApplicationResponseDTO> updateApplicationStatus(
            @PathVariable Long applicationId,
            @RequestParam ApplicationStatus status) {

        ApplicationResponseDTO response =
                applicationService.updateApplicationStatus(
                        applicationId, status);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ApplicationResponseDTO>> getApplicationsWithPagination(
            @RequestParam int page,
            @RequestParam int size) {

        Page<ApplicationResponseDTO> response =
                applicationService.getApplicationsWithPagination(page, size);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/sorting")
    public ResponseEntity<List<ApplicationResponseDTO>> getApplicationsWithSorting(
            @RequestParam String sortBy,
            @RequestParam String direction) {

        List<ApplicationResponseDTO> response =
                applicationService.getApplicationsWithSorting(sortBy, direction);

        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{applicationId}")
    public ResponseEntity<Void> deleteApplication(
            @PathVariable Long applicationId) {

        applicationService.deleteApplication(applicationId);

        return ResponseEntity.noContent().build();
    }
}