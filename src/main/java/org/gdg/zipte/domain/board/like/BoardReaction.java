package org.gdg.zipte.domain.board.like;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.user.member.Member;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardReaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static BoardReaction of(Board board, Member member, UserReaction reactionType) {
        BoardReaction reaction = BoardReaction.builder()
                .board(board)
                .member(member)
                .reactionType(reactionType)
                .build();

        board.addReaction(reaction);
        return reaction;
    }
}
