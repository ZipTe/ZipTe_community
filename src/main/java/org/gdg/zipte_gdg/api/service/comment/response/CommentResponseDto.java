package org.gdg.zipte_gdg.api.service.comment.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {

    private Long id;
    private String content;

    private Long memberId;
    private String author;

    private Long reviewId;
    private String reviewTitle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
