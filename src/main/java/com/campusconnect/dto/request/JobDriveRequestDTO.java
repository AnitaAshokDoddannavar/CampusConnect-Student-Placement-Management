package com.campusconnect.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JobDriveRequestDTO {

    @NotBlank(message = "Job title is required")
    private String jobTitle;

    @NotBlank(message = "Job description is required")
    private String description;

    @NotNull(message = "Minimum CGPA is required")
    @DecimalMin(value = "0.0", message = "Minimum CGPA cannot be less than 0")
    @DecimalMax(value = "10.0", message = "Minimum CGPA cannot be greater than 10")
    private Double minimumCgpa;

    @NotBlank(message = "Eligible department is required")
    private String eligibleDepartment;

    @NotNull(message = "Application deadline is required")
    @Future(message = "Application deadline must be a future date")
    private LocalDate applicationDeadline;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}