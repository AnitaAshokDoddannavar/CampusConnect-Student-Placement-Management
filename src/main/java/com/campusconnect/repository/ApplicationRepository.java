package com.campusconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long> 
{

    boolean existsByStudentStudentIdAndJobDriveJobDriveId(
            Long studentId,
            Long jobDriveId);
}