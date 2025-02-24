package com.zipte.platform.adapter.in.api.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import com.zipte.platform.domain.comment.CommentReaction;
import com.zipte.platform.domain.board.UserReaction;

@Data
@Builder
public class CommentReactionResponse {

    private Long author;

    private Long commentId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static CommentReactionResponse from(CommentReaction commentReaction) {
        return CommentReactionResponse.builder()
                .author(commentReaction.getMemberId())
                .commentId(commentReaction.getComment().getId())
                .reactionType(commentReaction.getReactionType())
                .build();
    }

}
