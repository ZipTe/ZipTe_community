package com.zipte.platform.adapter.in.api.dto.request.board;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import com.zipte.platform.domain.board.UserReaction;

@Data
public class CommentReactionRequest {

    private Long id;

    private Long memberId;

    private Long commentId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

}
