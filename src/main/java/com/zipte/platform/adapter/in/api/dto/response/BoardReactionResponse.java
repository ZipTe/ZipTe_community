package com.zipte.platform.adapter.in.api.dto.response;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;

@Data
@Builder
public class BoardReactionResponse {

    private Long author;

    private Long boardId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static BoardReactionResponse from(BoardReaction boardReaction) {
        return BoardReactionResponse.builder()
                .author(boardReaction.getMemberId())
                .boardId(boardReaction.getBoard().getId())
                .reactionType(boardReaction.getReactionType())
                .build();
    }

}
