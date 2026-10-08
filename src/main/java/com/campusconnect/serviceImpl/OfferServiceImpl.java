package com.campusconnect.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.dto.request.OfferRequestDTO;
import com.campusconnect.dto.response.OfferResponseDTO;
import com.campusconnect.entity.Application;
import com.campusconnect.entity.Offer;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.OfferMapper;
import com.campusconnect.repository.ApplicationRepository;
import com.campusconnect.repository.OfferRepository;
import com.campusconnect.service.OfferService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ApplicationRepository applicationRepository;
    private final OfferMapper offerMapper;

    @Override
    @Transactional
    public OfferResponseDTO createOffer(
            OfferRequestDTO offerRequestDTO) {

        Application application = applicationRepository
                .findById(offerRequestDTO.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with id: "
                                + offerRequestDTO.getApplicationId()));

        Offer offer = new Offer();

        offer.setApplication(application);
        offer.setOfferDate(offerRequestDTO.getOfferDate());
        offer.setSalary(offerRequestDTO.getSalary());
        offer.setStatus(offerRequestDTO.getStatus());

        Offer savedOffer =
                offerRepository.save(offer);

        return offerMapper.toResponseDTO(savedOffer);
    }

    @Override
    public OfferResponseDTO getOfferById(Long offerId) {

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Offer not found with id: "
                                + offerId));

        return offerMapper.toResponseDTO(offer);
    }

    @Override
    public List<OfferResponseDTO> getAllOffers() {

        return offerRepository.findAll()
                .stream()
                .map(offerMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public OfferResponseDTO updateOffer(
            Long offerId,
            OfferRequestDTO offerRequestDTO) {

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Offer not found with id: "
                                + offerId));

        Application application = applicationRepository
                .findById(offerRequestDTO.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with id: "
                                + offerRequestDTO.getApplicationId()));

        offer.setApplication(application);
        offer.setOfferDate(offerRequestDTO.getOfferDate());
        offer.setSalary(offerRequestDTO.getSalary());
        offer.setStatus(offerRequestDTO.getStatus());

        Offer updatedOffer =
                offerRepository.save(offer);

        return offerMapper.toResponseDTO(updatedOffer);
    }

    @Override
    public void deleteOffer(Long offerId) {

        Offer offer = offerRepository
                .findById(offerId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Offer not found with id: "
                                + offerId));

        offerRepository.delete(offer);
    }
}