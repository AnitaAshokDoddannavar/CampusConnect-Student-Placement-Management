package com.campusconnect.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDTO {

    private Long studentId;
    private String name;
    private String email;
    private String phone;
    private Double cgpa;
    private String department;
    private Integer graduationYear;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}