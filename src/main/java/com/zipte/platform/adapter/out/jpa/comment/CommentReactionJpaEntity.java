package com.zipte.platform.adapter.out.jpa.comment;

import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.comment.CommentReaction;
import com.zipte.platform.domain.user.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentReactionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private CommentJpaEntity comment;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // from
    public static CommentReactionJpaEntity from(CommentReaction commentReaction) {
        return CommentReactionJpaEntity.builder()
                .member(commentReaction.getMember())
                .comment(CommentJpaEntity.from(commentReaction.getComment()))
                .reactionType(commentReaction.getReactionType())
                .build();
    }

    // toDomain
    public CommentReaction toDomain() {
        return CommentReaction.builder()
                .id(this.getId())
                .member(this.getMember())
                .comment(CommentJpaEntity.toDomain(this.getComment()))
                .reactionType(this.getReactionType())
                .build();
    }

}
