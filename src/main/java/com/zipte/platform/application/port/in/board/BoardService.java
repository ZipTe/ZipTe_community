package com.zipte.platform.application.port.in.board;

import com.zipte.platform.adapter.in.api.dto.request.board.BoardRequest;
import com.zipte.platform.adapter.in.api.dto.response.BoardResponse;
import com.zipte.core.common.page.request.PageRequest;
import com.zipte.core.common.page.response.PageResponse;

public interface BoardService {


    // 게시물 생성하기 (카테고리 및 사진과 함께)
    BoardResponse create(BoardRequest request);

    // 게시물 수정


    // 게시글 삭제

    // 특정 카테고리에 있는 게시글 조회하기 (댓글은 숫자)
    PageResponse<BoardResponse> findByCategoryId(Long categoryId, PageRequest pageRequest);


    // 인기 게시글 조회하기 (댓글은 숫자)
    PageResponse<BoardResponse> findFavorite(PageRequest pageRequest);


    // 게시물 상세 조회
    BoardResponse findOne(Long boardId);


}
