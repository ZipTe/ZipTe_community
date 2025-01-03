package org.gdg.zipte.api.service.board.comment.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.domain.board.comment.Comment;

import java.util.*;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {

    private Long id;
    private String content;

    private String author;

    private String boardTitle;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public static CommentResponse from(Comment comment) {
        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getMember().getUsername())
                .boardTitle(comment.getBoard().getTitle())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    // 생성자
    public static List<CommentResponse> froms(List<Comment> comments) {
        List<CommentResponse> responses = new ArrayList<>();

        comments.forEach(comment -> responses.add(CommentResponse.from(comment)));
        return responses;

    }

}
