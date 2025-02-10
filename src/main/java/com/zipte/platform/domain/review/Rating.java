package com.zipte.platform.domain.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.apt.Apt;
import com.zipte.platform.domain.user.Member;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_id")
    private Apt apt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    // 교통 여건 평점
    private int transportConditionRating;

    // 주변 환경 평점
    private int environmentConditionRating;

    // 단지 관리 평점
    private int apartmentManagementRating;

    // 거주 환경 평점
    private int livingEnvironmentRating;

    // 전체 평점
    private int overallRating;


    // 생성 로직
    public static Rating of(Member member, Apt apt, int transportConditionRating, int environmentConditionRating, int apartmentManagementRating, int livingEnvironmentRating) {
        return Rating.builder()
                .member(member)
                .apt(apt)
                .transportConditionRating(transportConditionRating)
                .environmentConditionRating(environmentConditionRating)
                .apartmentManagementRating(apartmentManagementRating)
                .livingEnvironmentRating(livingEnvironmentRating)
                .build();
    }

//     비즈니스 로직
    public void addReview(Review review) {
        this.review = review;
    }

}
