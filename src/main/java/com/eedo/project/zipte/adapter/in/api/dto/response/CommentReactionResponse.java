package com.eedo.project.zipte.adapter.in.api.dto.response;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import com.eedo.project.zipte.domain.board.CommentReaction;
import com.eedo.project.zipte.domain.board.UserReaction;

@Data
@Builder
public class CommentReactionResponse {

    private String author;

    private Long commentId;

    @Enumerated(EnumType.STRING)
    private UserReaction reactionType;

    // 생성자
    public static CommentReactionResponse from(CommentReaction commentReaction) {
        return CommentReactionResponse.builder()
                .author(commentReaction.getMember().getUsername())
                .commentId(commentReaction.getComment().getId())
                .reactionType(commentReaction.getReactionType())
                .build();
    }

}
