package org.gdg.zipte_gdg.api.service.comment.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseWithReviewDto {

    private Long id;
    private Long memberId;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
