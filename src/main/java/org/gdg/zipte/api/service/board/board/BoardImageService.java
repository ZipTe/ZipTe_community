package org.gdg.zipte.api.service.board.board;

import org.gdg.zipte.domain.board.board.Board;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BoardImageService {

    List<String> saveFiles(Board board, List<MultipartFile> files);

    ResponseEntity<Resource> getFile(String fileName);

    void delelteFiles(List<String> fileNames);
}
