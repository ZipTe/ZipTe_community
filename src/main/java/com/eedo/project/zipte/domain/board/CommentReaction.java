package com.eedo.project.zipte.domain.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.user.Member;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Comment comment;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static CommentReaction of(Comment comment, Member member, UserReaction reactionType) {
        return CommentReaction.builder()
                .comment(comment)
                .member(member)
                .reactionType(reactionType)
                .build();
    }
}
