package org.gdg.zipte.api.service.board.commentReaction;

import org.gdg.zipte.api.controller.board.commentLike.request.CommentReactionRequest;
import org.gdg.zipte.api.service.board.commentReaction.response.CommentReactionResponse;

public interface CommentReactionService {

    // 추가하기
    CommentReactionResponse create(CommentReactionRequest request);

    // 취소하기
    CommentReactionResponse delete(CommentReactionRequest request);
}
