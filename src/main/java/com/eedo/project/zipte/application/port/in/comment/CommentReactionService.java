package com.eedo.project.zipte.application.port.in.comment;

import com.eedo.project.zipte.adapter.in.api.dto.request.board.CommentReactionRequest;
import com.eedo.project.zipte.adapter.in.api.dto.response.CommentReactionResponse;

public interface CommentReactionService {

    // 추가하기
    CommentReactionResponse create(CommentReactionRequest request);

    // 취소하기
    CommentReactionResponse delete(CommentReactionRequest request);
}
