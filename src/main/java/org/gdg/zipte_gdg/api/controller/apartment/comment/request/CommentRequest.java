package org.gdg.zipte_gdg.api.controller.apartment.comment.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentRequest {

    private Long id;
    private Long memberId;
    private Long reviewId;
    private String content;
    private LocalDateTime updatedAt;

}
