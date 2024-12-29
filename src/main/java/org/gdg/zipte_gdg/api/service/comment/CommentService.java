package org.gdg.zipte_gdg.api.service.comment;

import org.gdg.zipte_gdg.api.controller.comment.request.CommentRequestDto;
import org.gdg.zipte_gdg.api.service.comment.response.CommentResponseDto;
import org.gdg.zipte_gdg.domain.apartMent.comment.Comment;

public interface CommentService {

    // 댓글 등록
    CommentResponseDto register(CommentRequestDto commentRequestDto);

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
