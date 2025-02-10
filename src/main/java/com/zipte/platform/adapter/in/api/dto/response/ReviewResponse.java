package com.zipte.platform.adapter.in.api.dto.response;

import lombok.Builder;
import lombok.Data;
import com.zipte.platform.domain.review.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ReviewResponse {

    // 리뷰 작성자
    private String author;

    // 아파트 정보
    private String aptName;

    // 리뷰 내용
    private String title;
    private String content;

    // 리뷰 평점
    private RatingResponse rating;

    private int viewCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();


    public static ReviewResponse from(Review review) {
        return ReviewResponse.builder()
                .title(review.getTitle())
                .author(review.getMember().getUsername())
                .content(review.getContent())
                .aptName(review.getApt().getAptName())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt() != null ? review.getUpdatedAt() : review.getCreatedAt()) // null 처리
                .viewCount(review.getViewCount())
                .build();
    }


}
