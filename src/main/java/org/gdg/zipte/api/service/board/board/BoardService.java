package org.gdg.zipte.api.service.board.board;

import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.service.board.board.response.BoardResponseWithComment;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.domain.page.response.PageResponseDto;

public interface BoardService {


    // 게시물 생성하기 (카테고리 및 사진과 함께)
    BoardResponse create(BoardRequest request);

    // 게시물 수정


    // 게시글 삭제

    // 특정 카테고리에 있는 게시글 조회하기 (댓글은 숫자)
    PageResponseDto<BoardResponse> findAll(Long id, PageRequestDto pageRequestDto);


    // 인기 게시글 조회하기 (댓글은 숫자)
    PageResponseDto<BoardResponse> findFavorite(PageRequestDto pageRequestDto);


    // 게시물 상세 조회 (댓글과 함께)
    BoardResponseWithComment findOne(Long boardId);


}
