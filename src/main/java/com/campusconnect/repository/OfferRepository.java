package com.campusconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.campusconnect.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

}