package com.campusconnect.controller;

import java.io.IOException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.campusconnect.service.FileStorageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileStorageService fileStorageService;

    @PostMapping("/students/{studentId}/resume")
    public ResponseEntity<String> uploadResume(
            @PathVariable Long studentId,
            @RequestParam("file") MultipartFile file) throws IOException {

        String fileName = fileStorageService.uploadResume(studentId, file);
                
        return ResponseEntity.ok(
                "Resume uploaded successfully: " + fileName);
    }

    @GetMapping("/students/{studentId}/resume")
    public ResponseEntity<Resource> downloadResume(
            @PathVariable Long studentId) throws IOException {

        Resource resource = fileStorageService.downloadResume(studentId);
             
        return ResponseEntity.ok()
                .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\"" + resource.getFilename() + "\""
                )
                .body(resource);
    }
}