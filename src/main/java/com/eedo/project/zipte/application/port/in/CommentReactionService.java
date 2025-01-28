package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.board.CommentReactionRequest;
import com.eedo.project.zipte.representation.response.CommentReactionResponse;

public interface CommentReactionService {

    // 추가하기
    CommentReactionResponse create(CommentReactionRequest request);

    // 취소하기
    CommentReactionResponse delete(CommentReactionRequest request);
}
