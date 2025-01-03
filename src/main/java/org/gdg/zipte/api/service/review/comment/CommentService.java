package org.gdg.zipte.api.service.review.comment;

import org.gdg.zipte.api.controller.review.comment.request.CommentRequest;
import org.gdg.zipte.api.service.review.comment.response.CommentResponse;

public interface CommentService {

    // 댓글 등록
    CommentResponse register(CommentRequest commentRequest);

    // 댓글 수정

    // 댓글 삭제

    // 회원 댓글목록 조회

}
