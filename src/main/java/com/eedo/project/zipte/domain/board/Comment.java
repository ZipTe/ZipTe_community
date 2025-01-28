package com.eedo.project.zipte.domain.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.user.Member;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    // 대댓글을 위한 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Comment> children;

    // 생성된 날짜
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "comment")
    @Builder.Default
    private List<CommentReaction> reactions = new ArrayList<>();

    // 생성자
    public static Comment of(Board board, Member member, Comment parent, String content) {

        Comment comment = Comment.builder()
                .board(board)
                .member(member)
                .parent(parent)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        board.addComment(comment);

        return comment;
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
}
