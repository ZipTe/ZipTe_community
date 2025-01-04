package org.gdg.zipte.api.service.board.board;

import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;

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
