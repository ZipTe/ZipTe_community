package com.zipte.platform.application.port.in.board;

import com.zipte.platform.domain.board.Board;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CreateImageUseCase {

    // 사진 파일 저장하기
    List<String> saveFiles(Board board, List<MultipartFile> files);


}
