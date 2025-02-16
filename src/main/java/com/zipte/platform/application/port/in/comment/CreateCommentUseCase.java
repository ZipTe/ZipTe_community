package com.zipte.platform.application.port.in.comment;

import com.zipte.platform.application.port.in.dto.request.board.CommentRequest;
import com.zipte.platform.domain.comment.Comment;

public interface CreateCommentUseCase {

    // 댓글 등록
    Comment createComment(CommentRequest commentRequest);

}
