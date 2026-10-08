package com.campusconnect.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApplicationResponseDTO {

    private Long applicationId;

    private Long studentId;
    private String studentName;

    private Long jobDriveId;
    private String jobTitle;

    private Long companyId;
    private String companyName;

    private LocalDate applicationDate;

    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}