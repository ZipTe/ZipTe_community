package org.gdg.zipte_gdg.domain.review;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.comment.Comment;
import org.gdg.zipte_gdg.domain.member.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "review")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "review")
    @Builder.Default
    private List<ReviewImage> reviewImages = new ArrayList<>();

    private String title;

    private String content;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 생성 로직

    public static Review addNewReview(Member member, String title, String content) {

        Review review = Review.builder()
                .member(member)
                .title(title)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        member.addReview(review);
        return review;
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

}
