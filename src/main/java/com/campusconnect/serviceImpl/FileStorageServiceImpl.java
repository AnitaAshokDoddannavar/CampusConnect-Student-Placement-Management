package com.campusconnect.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.campusconnect.service.FileStorageService;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path uploadPath = Paths.get("uploads/resumes");

    @Override
    public String uploadResume(Long studentId, MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IOException("File is empty");
        }

        if (!file.getOriginalFilename().toLowerCase().endsWith(".pdf")) {
            throw new IOException("Only PDF files are allowed");
        }

        Files.createDirectories(uploadPath);

        String fileName = "student-" + studentId + "-resume.pdf";

        Path filePath = uploadPath.resolve(fileName);

        Files.copy(
                file.getInputStream(),
                filePath,
                StandardCopyOption.REPLACE_EXISTING
        );

        return fileName;
    }

    @Override
    public Resource downloadResume(Long studentId) throws IOException {

        String fileName = "student-" + studentId + "-resume.pdf";

        Path filePath = uploadPath.resolve(fileName);

        if (!Files.exists(filePath)) {
            throw new IOException("Resume not found for student: " + studentId);
        }

        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists() || !resource.isReadable()) {
            throw new IOException("File cannot be read");
        }

        return resource;
    }
}