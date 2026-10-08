package com.campusconnect.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferResponseDTO {

    private Long offerId;

    private Long applicationId;

    private Long studentId;

    private String studentName;

    private Long jobDriveId;

    private String jobTitle;

    private String companyName;

    private LocalDate offerDate;

    private Double salary;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}