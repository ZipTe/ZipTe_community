package org.gdg.zipte.api.controller.board.like.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.gdg.zipte.domain.board.like.UserReaction;

@Data
public class BoardReactionRequest {

    private Long id;

    private Long memberId;

    private Long boardId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

}
