package org.gdg.zipte.api.controller.board.comment.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {

    private Long id;
    private Long memberId;
    private Long boardId;
    private String content;
}
