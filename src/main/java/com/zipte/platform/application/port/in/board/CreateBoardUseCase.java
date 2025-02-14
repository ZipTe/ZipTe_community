package com.zipte.platform.application.port.in.board;

import com.zipte.platform.adapter.in.api.dto.request.board.BoardRequest;
import com.zipte.platform.domain.board.Board;

public interface CreateBoardUseCase {

    // 게시물 생성하기 (카테고리 및 사진과 함께)
    Board createBoard(BoardRequest request);



}
