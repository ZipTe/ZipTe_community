package com.zipte.platform.application.port.in.apt;

import com.zipte.platform.domain.review.Review;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewImageService {

    List<String> saveFiles(Review review, List<MultipartFile> files);

    ResponseEntity<Resource> getFile(String fileName);

    void delelteFiles(List<String> fileNames);
}
