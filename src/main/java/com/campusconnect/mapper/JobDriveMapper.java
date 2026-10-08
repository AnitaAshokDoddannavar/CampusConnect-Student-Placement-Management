package com.campusconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.campusconnect.dto.request.JobDriveRequestDTO;
import com.campusconnect.dto.response.JobDriveResponseDTO;
import com.campusconnect.entity.JobDrive;

@Mapper(componentModel = "spring")
public interface JobDriveMapper {

    @Mapping(target = "company", ignore = true)
    JobDrive toEntity(JobDriveRequestDTO jobDriveRequestDTO);

    @Mapping(source = "company.companyId", target = "companyId")
    @Mapping(source = "company.companyName", target = "companyName")
    JobDriveResponseDTO toResponseDTO(JobDrive jobDrive);

    @Mapping(target = "company", ignore = true)
    void updateJobDriveFromDto(
            JobDriveRequestDTO jobDriveRequestDTO,
            @MappingTarget JobDrive jobDrive);
}