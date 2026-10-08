package com.campusconnect.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.campusconnect.dto.request.ApplicationRequestDTO;
import com.campusconnect.dto.response.ApplicationResponseDTO;
import com.campusconnect.entity.ApplicationStatus;

public interface ApplicationService {

	ApplicationResponseDTO createApplication(
	        ApplicationRequestDTO applicationRequestDTO);

	ApplicationResponseDTO getApplicationById(Long applicationId);

	List<ApplicationResponseDTO> getAllApplications();

	ApplicationResponseDTO updateApplicationStatus(
	        Long applicationId,
	        ApplicationStatus status);

	Page<ApplicationResponseDTO> getApplicationsWithPagination(
	        int page, int size);

	List<ApplicationResponseDTO> getApplicationsWithSorting(
	        String sortBy, String direction);

	void deleteApplication(Long applicationId);
}