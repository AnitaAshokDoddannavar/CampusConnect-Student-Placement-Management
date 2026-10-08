package com.campusconnect.service;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String uploadResume(Long studentId, MultipartFile file) throws IOException;

    Resource downloadResume(Long studentId) throws IOException;
}