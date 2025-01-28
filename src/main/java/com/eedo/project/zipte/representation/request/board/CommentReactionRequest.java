package com.eedo.project.zipte.representation.request.board;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import com.eedo.project.zipte.domain.board.UserReaction;

@Data
public class CommentReactionRequest {

    private Long id;

    private Long memberId;

    private Long commentId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

}
