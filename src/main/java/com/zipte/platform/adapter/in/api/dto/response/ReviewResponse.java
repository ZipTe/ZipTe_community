package com.zipte.platform.adapter.in.api.dto.response;

import lombok.Builder;
import lombok.Data;
import com.zipte.platform.domain.review.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class ReviewResponse {

    // 아파트 정보
    private String aptId;

    // 리뷰 작성자
    private String author;

    // 리뷰 내용
    private String title;
    private String content;

    // 리뷰 평점
    private RatingResponse rating;

    private long viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<String> uploadFileNames = List.of(); // Immutable 빈 리스트 사용

    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .aptId(review.getAptId())
                .author(review.getMember().getUsername())
                .title(review.getSnippet().getTitle())
                .content(review.getSnippet().getContent())
                .rating(RatingResponse.of(review.getSnippet()))
                .viewCount(review.getStatistics().getViewCount())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

    public static List<ReviewResponse> from(List<Review> reviews) {
        return reviews.stream()
                .map(ReviewResponse::from)
                .collect(Collectors.toList());
    }
}
