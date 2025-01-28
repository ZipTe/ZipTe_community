package com.eedo.project.zipte.representation.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import com.eedo.project.zipte.domain.board.BoardReaction;
import com.eedo.project.zipte.domain.board.UserReaction;

@Data
@Builder
public class BoardReactionResponse {

    private String author;

    private Long boardId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static BoardReactionResponse from(BoardReaction boardReaction) {
        return BoardReactionResponse.builder()
                .author(boardReaction.getMember().getUsername())
                .boardId(boardReaction.getBoard().getId())
                .reactionType(boardReaction.getReactionType())
                .build();
    }

}
