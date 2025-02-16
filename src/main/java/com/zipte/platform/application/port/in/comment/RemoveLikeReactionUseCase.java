package com.zipte.platform.application.port.in.comment;


import com.zipte.platform.application.port.in.dto.request.board.CommentReactionRequest;

public interface RemoveLikeReactionUseCase {

    // 좋아요 제거하기
    void removeReaction(CommentReactionRequest request);



}
