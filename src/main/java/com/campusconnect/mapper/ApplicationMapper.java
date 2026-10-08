package com.campusconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.campusconnect.dto.response.ApplicationResponseDTO;
import com.campusconnect.entity.Application;

@Mapper(componentModel = "spring")
public interface ApplicationMapper {

    @Mapping(source = "student.studentId", target = "studentId")
    @Mapping(source = "student.name", target = "studentName")
    @Mapping(source = "jobDrive.jobDriveId", target = "jobDriveId")
    @Mapping(source = "jobDrive.jobTitle", target = "jobTitle")
    @Mapping(source = "jobDrive.company.companyId", target = "companyId")
    @Mapping(source = "jobDrive.company.companyName", target = "companyName")
    ApplicationResponseDTO toResponseDTO(Application application);
}