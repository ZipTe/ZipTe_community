package org.gdg.zipte_gdg.domain.review;

import jakarta.persistence.*;
import org.gdg.zipte_gdg.domain.member.Member;

import java.time.LocalDateTime;

@Entity
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String title;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
