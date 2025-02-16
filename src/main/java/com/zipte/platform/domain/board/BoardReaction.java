package com.zipte.platform.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardReaction {

    private Long id;
    private Long memberId;
    private Board board;
    private UserReaction reactionType;

    // 생성자
    public static BoardReaction of(Board board, Long member, UserReaction reactionType) {
        BoardReaction reaction = BoardReaction.builder()
                .board(board)
                .memberId(member)
                .reactionType(reactionType)
                .build();

        checkUserReaction(board, reactionType);

        return reaction;
    }

    // 메소드 따로 빼기
    private static void checkUserReaction(Board board, UserReaction reactionType) {
        if(reactionType == UserReaction.LIKE) {
            board.addLikeReaction();

        } else {
            board.removeLikeReaction();
        }
    }
}
