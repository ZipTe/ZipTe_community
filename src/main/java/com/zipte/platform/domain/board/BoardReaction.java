package com.zipte.platform.domain.board;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.user.Member;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BoardReaction {

    private Long id;
    private Member member;
    private Board board;
    private UserReaction reactionType;

    // 생성자
    public static BoardReaction of(Long id,Board board, Member member, UserReaction reactionType) {
        BoardReaction reaction = BoardReaction.builder()
                .id(id)
                .board(board)
                .member(member)
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
