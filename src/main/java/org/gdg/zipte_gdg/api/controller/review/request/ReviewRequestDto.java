package org.gdg.zipte_gdg.api.controller.review.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewRequestDto {

    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private LocalDateTime updatedAt;
}
