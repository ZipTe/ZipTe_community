package com.eedo.project.zipte.application.service.board;

import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import com.eedo.project.zipte.application.port.in.board.BoardImageService;
import com.eedo.project.zipte.domain.board.Board;
import com.eedo.project.zipte.domain.board.BoardImage;
import com.eedo.project.zipte.adapter.out.persistence.jpa.board.BoardImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardImageServiceImpl implements BoardImageService {

    private final BoardImageRepository boardImageRepository;
    @Value("${spring.file.upload.board.path}") // 프레임워크 value를 사용해야한다.
    private String uploadPath;

    @PostConstruct // 폴더 없으면 생성!
    public void init() {
        File tempFolder = new File(uploadPath);
        if (!tempFolder.exists()){
            tempFolder.mkdir();
        }

        uploadPath = tempFolder.getAbsolutePath();
        log.info(uploadPath);
    }

    @Override
    public List<String> saveFiles(Board board, List<MultipartFile> files) {

        // files가 null일 경우 빈 리스트 반환
        if (files == null || files.isEmpty()) {
            return List.of();
        }


        List<String> uploadNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, savedName);

            BoardImage boardImage = BoardImage.of(board, savedName);
            boardImageRepository.save(boardImage);// 먼저 저장

            uploadNames.add(savedName);

            try {
                Files.copy(file.getInputStream(), savePath);

                String contentType = file.getContentType();
                if (contentType != null || contentType.startsWith("image")) {

                    // S이름 붙여서 수정
                    Path ThumbnailPath = Paths.get(uploadPath, "s_" + savedName);

                    // 썸네일 200으로 수정
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(ThumbnailPath.toFile());

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return uploadNames;
    }

    @Override
    public ResponseEntity<Resource> getFile(String fileName) {
        Resource resource = new FileSystemResource(uploadPath + File.separator + fileName);

        if (!resource.isReadable()) {
            resource = new FileSystemResource(uploadPath + File.separator + "default.jpeg");
        }

        HttpHeaders httpHeaders = new HttpHeaders();

        try {
            httpHeaders.add("Content-Type", Files.probeContentType(resource.getFile().toPath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok().headers(httpHeaders).body(resource);
    }

    @Override
    public void delelteFiles(List<String> fileNames) {
        if (fileNames == null || fileNames.isEmpty()) {
            return;
        }

        fileNames.forEach(fileName->{
            String thumbnailFileName = "s+" + fileName;

            Path thumbnailPath = Paths.get(uploadPath, thumbnailFileName);
            Path filePath = Paths.get(uploadPath, fileName);

            // 존재한다면 삭제 하는 기능
            try {
                Files.deleteIfExists(filePath);
                Files.deleteIfExists(thumbnailPath);
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        });


    }
}
