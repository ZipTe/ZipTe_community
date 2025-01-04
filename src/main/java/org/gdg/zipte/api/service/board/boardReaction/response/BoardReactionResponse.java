package org.gdg.zipte.api.service.board.boardReaction.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.domain.board.reaction.BoardReaction;
import org.gdg.zipte.domain.board.reaction.UserReaction;

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
