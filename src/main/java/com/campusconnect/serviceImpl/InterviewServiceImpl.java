package com.campusconnect.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.campusconnect.dto.request.InterviewRequestDTO;
import com.campusconnect.dto.response.InterviewResponseDTO;
import com.campusconnect.entity.Application;
import com.campusconnect.entity.Interview;
import com.campusconnect.exception.ResourceNotFoundException;
import com.campusconnect.mapper.InterviewMapper;
import com.campusconnect.repository.ApplicationRepository;
import com.campusconnect.repository.InterviewRepository;
import com.campusconnect.service.InterviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final ApplicationRepository applicationRepository;
    private final InterviewMapper interviewMapper;

    @Override
    @Transactional
    public InterviewResponseDTO createInterview(
            InterviewRequestDTO interviewRequestDTO) {

        Application application = applicationRepository
                .findById(interviewRequestDTO.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with id: "
                                + interviewRequestDTO.getApplicationId()));

        Interview interview = new Interview();

        interview.setApplication(application);
        interview.setInterviewDate(
                interviewRequestDTO.getInterviewDate());
        interview.setInterviewType(
                interviewRequestDTO.getInterviewType());
        interview.setResult(
                interviewRequestDTO.getResult());

        Interview savedInterview =
                interviewRepository.save(interview);

        return interviewMapper.toResponseDTO(savedInterview);
    }

    @Override
    public InterviewResponseDTO getInterviewById(Long interviewId) {

        Interview interview = interviewRepository
                .findById(interviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found with id: "
                                + interviewId));

        return interviewMapper.toResponseDTO(interview);
    }

    @Override
    public List<InterviewResponseDTO> getAllInterviews() {

        return interviewRepository.findAll()
                .stream()
                .map(interviewMapper::toResponseDTO)
                .toList();
    }

    @Override
    @Transactional
    public InterviewResponseDTO updateInterview(
            Long interviewId,
            InterviewRequestDTO interviewRequestDTO) {

        Interview interview = interviewRepository
                .findById(interviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found with id: "
                                + interviewId));

        Application application = applicationRepository
                .findById(interviewRequestDTO.getApplicationId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Application not found with id: "
                                + interviewRequestDTO.getApplicationId()));

        interview.setApplication(application);
        interview.setInterviewDate(
                interviewRequestDTO.getInterviewDate());
        interview.setInterviewType(
                interviewRequestDTO.getInterviewType());
        interview.setResult(
                interviewRequestDTO.getResult());

        Interview updatedInterview =
                interviewRepository.save(interview);

        return interviewMapper.toResponseDTO(updatedInterview);
    }

    @Override
    public void deleteInterview(Long interviewId) {

        Interview interview = interviewRepository
                .findById(interviewId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Interview not found with id: "
                                + interviewId));

        interviewRepository.delete(interview);
    }
}