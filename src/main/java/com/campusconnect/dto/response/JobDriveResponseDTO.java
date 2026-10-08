package com.campusconnect.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDriveResponseDTO {

    private Long jobDriveId;

    private String jobTitle;

    private String description;

    private Double minimumCgpa;

    private String eligibleDepartment;

    private LocalDate applicationDeadline;

    private Long companyId;

    private String companyName;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}