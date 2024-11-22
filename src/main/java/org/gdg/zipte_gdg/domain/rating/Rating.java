package org.gdg.zipte_gdg.domain.rating;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apt.Apt;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.review.Review;

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

    private int rating;

    // 생성 로직
    public static Rating createRating(Member member, Apt apt, int ratingScore) {
        Rating rating = Rating.builder()
                .member(member)
                .apt(apt)
                .rating(ratingScore)
                .build();

        return rating;
    }

//     비즈니스 로직
    public void addReview(Review review) {
        this.review = review;
    }

}
