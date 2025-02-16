package com.zipte.platform.application.port.in.comment;


import com.zipte.platform.domain.user.Member;

public interface CommentBlockUseCase {

    // 댓글 차단하기
    void blockComment(Member member, Long commentId);
}
