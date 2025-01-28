package com.eedo.project.zipte.domain.board;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.user.Member;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private int viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    @Builder.Default
    private List<BoardReaction> reactions = new ArrayList<>();

    // 생성자
    public static Board of(String title, String content, Member member) {

        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 양방향
    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addReaction(BoardReaction reaction) {
        reactions.add(reaction);
    }

    // 조회수 더하기
    public void addCount() {
        this.viewCount++;
    }

    // 좋아요 수 계산 메서드
    public Long getLikeCount() {
        return reactions.stream()
                .filter(reaction -> reaction.getReactionType() == UserReaction.LIKE)
                .count();
    }

    // 싫어요 수 계산 메서드
    public Long getDisLikeCount() {
        return reactions.stream()
                .filter(reaction -> reaction.getReactionType() == UserReaction.DISLIKE)
                .count();
    }

    // 좋아요 - 싫어요 결과 계산
    public Long getReactionScore() {
        return getLikeCount() - getDisLikeCount();
    }





}
