package com.campusconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.campusconnect.dto.request.CompanyRequestDTO;
import com.campusconnect.dto.response.CompanyResponseDTO;
import com.campusconnect.entity.Company;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company toEntity(CompanyRequestDTO companyRequestDTO);

    CompanyResponseDTO toResponseDTO(Company company);

    void updateCompanyFromDto(
            CompanyRequestDTO companyRequestDTO,
            @MappingTarget Company company);
}