package com.campusconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> 
{

}