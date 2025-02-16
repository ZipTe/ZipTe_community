package com.zipte.platform.application.port.in.dto.request.board;

import com.zipte.platform.domain.board.UserReaction;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class BoardReactionRequest {

    private Long id;

    private Long memberId;

    private Long boardId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

}
