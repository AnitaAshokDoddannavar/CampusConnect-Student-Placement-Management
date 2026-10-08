package com.campusconnect.scheduler;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.campusconnect.entity.JobDrive;
import com.campusconnect.repository.JobDriveRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JobDriveScheduler {

    private static final Logger logger =
            LoggerFactory.getLogger(JobDriveScheduler.class);

    private final JobDriveRepository jobDriveRepository;

    @Scheduled(fixedRate = 30000)
    public void checkJobDriveDeadlines() {

        logger.info("JobDriveScheduler is running - checking job drive deadlines");

        List<JobDrive> jobDrives =
                jobDriveRepository.findAll();

        LocalDate today = LocalDate.now();

        for (JobDrive jobDrive : jobDrives) {

            if (jobDrive.getApplicationDeadline() != null
                    && jobDrive.getApplicationDeadline().isBefore(today)) {

                logger.info(
                        "Job drive deadline has expired: {}",
                        jobDrive.getJobDriveId());
            }
        }
    }
}