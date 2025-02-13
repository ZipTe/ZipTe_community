package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardReactionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardJpaEntity board;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // from
    public static BoardReactionJpaEntity from(BoardReaction boardReaction) {
        return BoardReactionJpaEntity.builder()
                .id(boardReaction.getId())
                .member(boardReaction.getMember())
                .board(BoardJpaEntity.from(boardReaction.getBoard()))
                .reactionType(boardReaction.getReactionType())
                .build();
    }

    // toDomain
    public BoardReaction toDomain(BoardReactionJpaEntity entity) {
        return BoardReaction.of(entity.getId(), entity.getBoard().toDomain(), entity.getMember(), entity.getReactionType());
    }


}
