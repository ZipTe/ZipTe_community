package com.zipte.platform.adapter.in.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import com.zipte.platform.domain.board.Comment;

import java.util.*;
import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponse {

    private Long id;
    private String content;
    private String author;

    @Builder.Default
    private Boolean isWriter = false;

    private Long likeCount;

    private Long dislikeCount;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<CommentResponse> children = new ArrayList<>();

    // 생성자
    public static CommentResponse from(Comment comment, String writer) {
        // 부모 댓글 생성
        CommentResponse response = CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .author(comment.getMember().getUsername())
                .likeCount(comment.getLikeCount() != null ? comment.getLikeCount() : 0)
                .dislikeCount(comment.getDisLikeCount() != null ? comment.getDisLikeCount() : 0)
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .children(createChildren(comment.getChildren(), writer)) // 대댓글 처리
                .build();

        // 댓글 작성자와 글 작성자가 동일한지 확인
        if (Objects.equals(response.getAuthor(), writer)) {
            response.setIsWriter(true);
        }

        return response;
    }

    // 자식 댓글들을 처리하는 메서드
    private static List<CommentResponse> createChildren(List<Comment> children, String writer) {
        // children이 null일 경우 빈 리스트를 반환하도록 처리
        if (children == null) {
            return new ArrayList<>();
        }

        List<CommentResponse> childrenResponses = new ArrayList<>();

        for (Comment child : children) {
            CommentResponse childResponse = CommentResponse.builder()
                    .id(child.getId())
                    .content(child.getContent())
                    .author(child.getMember().getUsername())
                    .createdAt(child.getCreatedAt())
                    .updatedAt(child.getUpdatedAt())
                    .build();

            // 대댓글 작성자와 글 작성자가 동일한지 확인
            if (Objects.equals(childResponse.getAuthor(), writer)) {
                childResponse.setIsWriter(true);
            }

            childrenResponses.add(childResponse);
        }

        return childrenResponses;
    }

    // 리스트 변환 메서드
    public static List<CommentResponse> froms(List<Comment> comments, String writer) {
        List<CommentResponse> responses = new ArrayList<>();
        comments.forEach(comment -> responses.add(from(comment, writer)));
        return responses;
    }
}
