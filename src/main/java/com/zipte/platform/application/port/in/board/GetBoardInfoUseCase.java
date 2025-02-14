package com.zipte.platform.application.port.in.board;

import com.zipte.core.common.page.request.PageRequest;
import com.zipte.platform.domain.board.Board;
import org.springframework.data.domain.Page;

public interface GetBoardInfoUseCase {

    // 특정 카테고리에 있는 게시글 조회하기 (댓글은 숫자)
    Page<Board> getByCategoryId(Long categoryId, PageRequest pageRequest);

    // 게시물 상세 조회
    Board getOneInfo(Long boardId);

}
