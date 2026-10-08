package com.campusconnect.dto.response;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyResponseDTO {

    private Long companyId;

    private String companyName;

    private String email;

    private String phone;

    private String industry;

    private String location;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}