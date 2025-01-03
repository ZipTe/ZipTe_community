package org.gdg.zipte_gdg.domain.review.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.apt.apt.Apt;
import org.gdg.zipte_gdg.domain.review.comment.Comment;
import org.gdg.zipte_gdg.domain.user.member.Member;
import org.gdg.zipte_gdg.domain.review.rating.Rating;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apt_id")
    private Apt apt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id")
    private Rating rating;

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private int viewCount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "review")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    @Builder.Default
    private List<ReviewImage> reviewImages = new ArrayList<>();


    // 생성 로직
    public static Review of(Member member, Apt apt, String title, String content, Rating rating) {

        return Review.builder()
                .member(member)
                .apt(apt)
                .title(title)
                .content(content)
                .rating(rating)
                .createdAt(LocalDateTime.now())
                .build();

    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addReviewImage(ReviewImage reviewImage) {
        this.reviewImages.add(reviewImage);
        reviewImage.setReview(this);
        reviewImage.setOrd(this.reviewImages.size()-1);
    }

    public void removeReviewImage(ReviewImage reviewImage) {
        this.reviewImages.remove(reviewImage);
    }

    public void addCount() {
        this.viewCount++;
    }

}
