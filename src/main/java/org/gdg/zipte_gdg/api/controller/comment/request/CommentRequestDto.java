package org.gdg.zipte_gdg.api.controller.comment.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentRequestDto {

    private Long id;
    private Long memberId;
    private Long reviewId;
    private String content;
    private LocalDateTime updatedAt;

}
