package com.campusconnect.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfferRequestDTO {

    @NotNull(message = "Application ID is required")
    private Long applicationId;

    @NotNull(message = "Offer date is required")
    @FutureOrPresent(message = "Offer date cannot be in the past")
    private LocalDate offerDate;

    @NotNull(message = "Salary is required")
    @DecimalMin(value = "0.0", message = "Salary cannot be negative")
    private Double salary;

    @NotNull(message = "Offer status is required")
    private String status;
}