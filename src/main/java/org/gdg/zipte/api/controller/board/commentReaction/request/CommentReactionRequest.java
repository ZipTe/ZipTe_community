package org.gdg.zipte.api.controller.board.commentReaction.request;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.gdg.zipte.domain.board.reaction.UserReaction;

@Data
public class CommentReactionRequest {

    private Long id;

    private Long memberId;

    private Long commentId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

}
