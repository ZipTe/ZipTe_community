package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.domain.review.Review;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewImageService {

    List<String> saveFiles(Review review, List<MultipartFile> files);

    ResponseEntity<Resource> getFile(String fileName);

    void delelteFiles(List<String> fileNames);
}
