package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.request.CompanyRequestDTO;
import com.campusconnect.dto.response.CompanyResponseDTO;

public interface CompanyService {

    CompanyResponseDTO createCompany(
            CompanyRequestDTO companyRequestDTO);

    CompanyResponseDTO getCompanyById(Long companyId);

    List<CompanyResponseDTO> getAllCompanies();

    CompanyResponseDTO updateCompany(
            Long companyId,
            CompanyRequestDTO companyRequestDTO);

    void deleteCompany(Long companyId);
}