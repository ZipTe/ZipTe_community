package com.zipte.platform.adapter.out.jpa.comment;

import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.comment.CommentReaction;
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

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private CommentJpaEntity comment;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // from
    public static CommentReactionJpaEntity from(CommentReaction commentReaction) {
        return CommentReactionJpaEntity.builder()
                .memberId(commentReaction.getMemberId())
                .comment(CommentJpaEntity.from(commentReaction.getComment()))
                .reactionType(commentReaction.getReactionType())
                .build();
    }

    // toDomain
    public CommentReaction toDomain() {
        return CommentReaction.builder()
                .id(this.getId())
                .memberId(this.getMemberId())
                .comment(CommentJpaEntity.toDomain(this.getComment()))
                .reactionType(this.getReactionType())
                .build();
    }

}
