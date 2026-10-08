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

import com.campusconnect.dto.request.InterviewRequestDTO;
import com.campusconnect.dto.response.InterviewResponseDTO;
import com.campusconnect.service.InterviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponseDTO> createInterview(
            @Valid @RequestBody InterviewRequestDTO interviewRequestDTO) {

        InterviewResponseDTO response =
                interviewService.createInterview(interviewRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{interviewId}")
    public ResponseEntity<InterviewResponseDTO> getInterviewById(
            @PathVariable Long interviewId) {

        InterviewResponseDTO response =
                interviewService.getInterviewById(interviewId);

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<InterviewResponseDTO>> getAllInterviews() {

        List<InterviewResponseDTO> response =
                interviewService.getAllInterviews();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{interviewId}")
    public ResponseEntity<InterviewResponseDTO> updateInterview(
            @PathVariable Long interviewId,
            @Valid @RequestBody InterviewRequestDTO interviewRequestDTO) {

        InterviewResponseDTO response =
                interviewService.updateInterview(
                        interviewId,
                        interviewRequestDTO);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{interviewId}")
    public ResponseEntity<Void> deleteInterview(
            @PathVariable Long interviewId) {

        interviewService.deleteInterview(interviewId);

        return ResponseEntity.noContent().build();
    }
}