package com.campusconnect.entity;

import java.time.LocalDate;

import com.campusconnect.audit.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class JobDrive extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobDriveId;

    private String jobTitle;

    private String description;

    private Double minimumCgpa;

    private String eligibleDepartment;

    private LocalDate applicationDeadline;

    @ManyToOne
    private Company company;
}