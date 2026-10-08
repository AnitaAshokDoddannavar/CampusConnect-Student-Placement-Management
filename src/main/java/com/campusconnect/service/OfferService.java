package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.request.OfferRequestDTO;
import com.campusconnect.dto.response.OfferResponseDTO;

public interface OfferService {

    OfferResponseDTO createOffer(
            OfferRequestDTO offerRequestDTO);

    OfferResponseDTO getOfferById(Long offerId);

    List<OfferResponseDTO> getAllOffers();

    OfferResponseDTO updateOffer(
            Long offerId,
            OfferRequestDTO offerRequestDTO);

    void deleteOffer(Long offerId);
}