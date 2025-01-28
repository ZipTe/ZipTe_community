package com.eedo.project.zipte.domain.review;

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
public class ReviewImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rimage_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;

    private String fileName;

    private int ord;


    public static ReviewImage of(Review review, String fileName) {
        return ReviewImage.builder()
                .review(review)
                .fileName(fileName)
                .ord(0)
                .build();
    }


    //로직
    public void setReview(Review review) {
        this.review = review;
    }

    public void setOrd(int ord) {
        this.ord = ord;
    }
}
