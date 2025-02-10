package com.eedo.project.zipte.application.port.in.comment;

import com.eedo.project.zipte.adapter.in.api.dto.request.board.CommentRequest;
import com.eedo.project.zipte.adapter.in.api.dto.response.CommentResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;

public interface CommentService {

    // 댓글 등록
    CommentResponse register(CommentRequest commentRequest);

    // 댓글 조회
    PageResponse<CommentResponse> findAll(Long boardId, PageRequest pageRequest);

    // 댓글 수정

    // 댓글 삭제

    // 회원 댓글목록 조회

}
