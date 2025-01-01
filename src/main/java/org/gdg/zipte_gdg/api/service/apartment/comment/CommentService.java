package org.gdg.zipte_gdg.api.service.apartment.comment;

import org.gdg.zipte_gdg.api.controller.apartment.comment.request.CommentRequest;
import org.gdg.zipte_gdg.api.service.apartment.comment.response.CommentResponseDto;
import org.gdg.zipte_gdg.domain.apartment.comment.Comment;

public interface CommentService {

    // 댓글 등록
    CommentResponseDto register(CommentRequest commentRequest);

    // 댓글 수정

    // 댓글 삭제

    // 댓글 목록

    // 회원 댓글목록 조회


    default CommentResponseDto entityToDto(Comment comment) {

        return CommentResponseDto.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .memberId(comment.getMember().getId())
                .author(comment.getMember().getUsername())
                .reviewId(comment.getReview().getId())
                .reviewTitle(comment.getReview().getTitle())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

}
