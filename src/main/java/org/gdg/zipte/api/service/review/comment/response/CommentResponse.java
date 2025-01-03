package org.gdg.zipte.api.service.review.comment.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.domain.review.comment.Comment;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {

    private Long id;
    private String content;

    private Long memberId;
    private String author;

    private Long reviewId;
    private String reviewTitle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .memberId(comment.getMember().getId())
                .author(comment.getMember().getUsername())
                .reviewId(comment.getReview().getId())
                .reviewTitle(comment.getReview().getTitle())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
