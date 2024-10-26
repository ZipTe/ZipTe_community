package org.gdg.zipte_gdg.api.service.review;

import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.review.request.ReviewRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseDto;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseWithCommentDto;
import org.gdg.zipte_gdg.domain.review.Review;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public interface ReviewService {

    // 리뷰 등록
    ReviewResponseDto register(ReviewRequestDto reviewRequestDto);

    // 리뷰 수정

    // 리뷰 및 댓글 조회
    ReviewResponseWithCommentDto getOne(Long id);

    // 리뷰 목록 조회 <페이징 처리해서 가져오기>
    PageResponseDto<ReviewResponseDto> getList(PageRequestDto pageRequestDto);

    // 특정 유저 리뷰 조회 <특정 아이디 리뷰 검색해서 가져오기>
    PageResponseDto<ReviewResponseDto> getReviewsByMemberId(PageRequestDto pageRequestDto, Long memberId);

    // 리뷰 삭제


    default ReviewResponseDto entityToDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .author(review.getMember().getUsername())
                .content(review.getContent())
                .createdAt(Date.from(review.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }

    default ReviewResponseWithCommentDto entityToCommentDto(Review review) {
        return ReviewResponseWithCommentDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .author(review.getMember().getUsername())
                .content(review.getContent())
                .createdAt(Date.from(review.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()))
                .build();
    }

}
