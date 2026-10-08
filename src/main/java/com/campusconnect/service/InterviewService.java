package com.campusconnect.service;

import java.util.List;

import com.campusconnect.dto.request.InterviewRequestDTO;
import com.campusconnect.dto.response.InterviewResponseDTO;

public interface InterviewService {

    InterviewResponseDTO createInterview(
            InterviewRequestDTO interviewRequestDTO);

    InterviewResponseDTO getInterviewById(Long interviewId);

    List<InterviewResponseDTO> getAllInterviews();

    InterviewResponseDTO updateInterview(
            Long interviewId,
            InterviewRequestDTO interviewRequestDTO);

    void deleteInterview(Long interviewId);
}