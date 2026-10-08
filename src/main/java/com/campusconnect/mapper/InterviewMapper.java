package com.campusconnect.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.campusconnect.dto.response.InterviewResponseDTO;
import com.campusconnect.entity.Interview;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    @Mapping(source = "application.applicationId", target = "applicationId")
    @Mapping(source = "application.student.studentId", target = "studentId")
    @Mapping(source = "application.student.name", target = "studentName")
    @Mapping(source = "application.jobDrive.jobDriveId", target = "jobDriveId")
    @Mapping(source = "application.jobDrive.jobTitle", target = "jobTitle")
    @Mapping(source = "application.jobDrive.company.companyName", target = "companyName")
    InterviewResponseDTO toResponseDTO(Interview interview);
}