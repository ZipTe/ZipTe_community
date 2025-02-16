package com.zipte.platform.adapter.out.jpa.review;

import com.zipte.platform.adapter.out.jpa.BaseEntity;
import com.zipte.platform.domain.review.Review;
import com.zipte.platform.domain.user.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ReviewJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "apt_id")
    private String aptId;

    @Embedded
    private ReviewSnippetEntity snippet;

    @Embedded
    private ReviewStatisticsEntity statistics;

    // from
    public static ReviewJpaEntity from(Review review) {
        return ReviewJpaEntity.builder()
                .member(review.getMember())
                .aptId(review.getAptId()) // MongoDB의 AptDocument ID만 저장
                .snippet(ReviewSnippetEntity.from(review.getSnippet()))
                .statistics(ReviewStatisticsEntity.from(review.getStatistics()))
                .build();
    }

    // toDomain -
    public Review toDomain() {
        return Review.builder()
                .id(this.getId())
                .member(this.getMember())
                .aptId(this.getAptId())
                .snippet(this.getSnippet().toDomain())
                .statistics(this.getStatistics().toDomain())
                .build();
    }
}
