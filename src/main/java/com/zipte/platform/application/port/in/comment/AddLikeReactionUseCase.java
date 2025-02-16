package com.zipte.platform.application.port.in.comment;

import com.zipte.platform.application.port.in.dto.request.board.CommentReactionRequest;
import com.zipte.platform.domain.comment.CommentReaction;

public interface AddLikeReactionUseCase {

    // 좋아요 추가하기
    CommentReaction addReaction(CommentReactionRequest request);




}
