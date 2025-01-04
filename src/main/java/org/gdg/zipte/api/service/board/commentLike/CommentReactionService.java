package org.gdg.zipte.api.service.board.commentLike;

import org.gdg.zipte.api.controller.board.commentLike.request.CommentReactionRequest;
import org.gdg.zipte.api.service.board.commentLike.response.CommentReactionResponse;

public interface CommentReactionService {

    // 추가하기
    CommentReactionResponse create(CommentReactionRequest request);

    // 취소하기
    CommentReactionResponse delete(CommentReactionRequest request);
}
