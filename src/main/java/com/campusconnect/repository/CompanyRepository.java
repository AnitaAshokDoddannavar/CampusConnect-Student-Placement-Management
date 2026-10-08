package com.campusconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> 
{

}