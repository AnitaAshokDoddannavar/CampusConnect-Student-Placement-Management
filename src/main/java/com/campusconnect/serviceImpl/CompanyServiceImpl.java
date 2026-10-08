package com.campusconnect.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.campusconnect.dto.request.CompanyRequestDTO;
import com.campusconnect.dto.response.CompanyResponseDTO;
import com.campusconnect.entity.Company;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.CompanyMapper;
import com.campusconnect.repository.CompanyRepository;
import com.campusconnect.service.CompanyService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public CompanyResponseDTO createCompany(
            CompanyRequestDTO companyRequestDTO) {

        Company company = companyMapper.toEntity(companyRequestDTO);

        Company savedCompany = companyRepository.save(company);

        return companyMapper.toResponseDTO(savedCompany);
    }

    @Override
    public CompanyResponseDTO getCompanyById(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Company not found with id: " + companyId));

        return companyMapper.toResponseDTO(company);
    }

    @Override
    public List<CompanyResponseDTO> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(companyMapper::toResponseDTO)
                .toList();
    }

    @Override
    public CompanyResponseDTO updateCompany(
            Long companyId,
            CompanyRequestDTO companyRequestDTO) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Company not found with id: " + companyId));

        companyMapper.updateCompanyFromDto(
                companyRequestDTO, company);

        Company updatedCompany = companyRepository.save(company);

        return companyMapper.toResponseDTO(updatedCompany);
    }

    @Override
    public void deleteCompany(Long companyId) {

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Company not found with id: " + companyId));

        companyRepository.delete(company);
    }
}