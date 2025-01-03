package org.gdg.zipte.api.controller.review.comment.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {

    private Long id;
    private Long memberId;
    private Long reviewId;
    private String content;
}
