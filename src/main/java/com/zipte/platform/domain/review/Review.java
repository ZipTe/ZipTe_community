package com.zipte.platform.domain.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Review {

    private Long id;

    private Long memberId;
    private String aptId;

    private ReviewSnippet snippet;
    private ReviewStatistics statistics;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성 로직
    public static Review of(Long memberId, String aptId, ReviewSnippet snippet, ReviewStatistics statistics) {

        return Review.builder()
                .memberId(memberId)
                .aptId(aptId)
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

}
