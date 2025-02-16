package com.zipte.platform.application.port.out.comment;

import com.zipte.platform.domain.comment.Comment;

public interface SaveCommentPort {

    Comment saveComment(Comment comment);

}
