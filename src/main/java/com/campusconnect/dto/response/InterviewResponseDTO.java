package com.campusconnect.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewResponseDTO {

    private Long interviewId;

    private Long applicationId;

    private Long studentId;

    private String studentName;

    private Long jobDriveId;

    private String jobTitle;

    private String companyName;

    private LocalDateTime interviewDate;

    private String interviewType;

    private String result;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}