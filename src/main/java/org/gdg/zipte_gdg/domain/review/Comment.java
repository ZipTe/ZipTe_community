package org.gdg.zipte_gdg.domain.review;

import jakarta.persistence.*;
import org.gdg.zipte_gdg.domain.member.Member;

import java.time.LocalDateTime;


@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
