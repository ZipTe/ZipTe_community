package com.zipte.platform.domain.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.apt.Apt;
import com.zipte.platform.domain.user.Member;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Review {

    private Long id;

    private Member member;
    private String aptId;

    private ReviewSnippet snippet;
    private ReviewStatistics statistics;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성 로직
    public static Review of(Member member, String aptId, ReviewSnippet snippet, ReviewStatistics statistics) {

        return Review.builder()
                .member(member)
                .aptId(aptId)
                .snippet(snippet)
                .statistics(statistics)
                .build();
    }

}
