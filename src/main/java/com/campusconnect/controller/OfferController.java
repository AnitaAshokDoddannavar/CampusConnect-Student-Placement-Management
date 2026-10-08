package com.campusconnect.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.campusconnect.dto.request.OfferRequestDTO;
import com.campusconnect.dto.response.OfferResponseDTO;
import com.campusconnect.service.OfferService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/offers")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public ResponseEntity<OfferResponseDTO> createOffer(
            @Valid @RequestBody OfferRequestDTO offerRequestDTO) {

        OfferResponseDTO response =
                offerService.createOffer(offerRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> getOfferById(
            @PathVariable Long offerId) {

        OfferResponseDTO response =
                offerService.getOfferById(offerId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<OfferResponseDTO>> getAllOffers() {

        List<OfferResponseDTO> response =
                offerService.getAllOffers();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{offerId}")
    public ResponseEntity<OfferResponseDTO> updateOffer(
            @PathVariable Long offerId,
            @Valid @RequestBody OfferRequestDTO offerRequestDTO) {

        OfferResponseDTO response =
                offerService.updateOffer(
                        offerId,
                        offerRequestDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{offerId}")
    public ResponseEntity<Void> deleteOffer(
            @PathVariable Long offerId) {

        offerService.deleteOffer(offerId);

        return ResponseEntity.noContent().build();
    }
}