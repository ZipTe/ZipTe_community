package org.gdg.zipte_gdg.api.service.apartment.review;

import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.apartment.review.request.ReviewRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.apartment.review.response.ReviewResponseDto;
import org.gdg.zipte_gdg.api.service.apartment.review.response.ReviewResponseWithCommentDto;
import org.gdg.zipte_gdg.domain.apartment.review.Review;

import java.time.ZoneId;
import java.util.Date;

public interface ReviewService {

    // 리뷰 등록
    ReviewResponseDto register(ReviewRequestDto reviewRequestDto);

    // 리뷰 수정

    // 리뷰 및 댓글 조회
    ReviewResponseWithCommentDto getOne(Long id);

    // 특정 아파트 리뷰 목록 조회 (최신) <페이징 처리>
    PageResponseDto<ReviewResponseDto> getListByAptId(PageRequestDto pageRequestDto, Long aptId);

    // 특정 아파트 리뷰 목록 조회 (조회수) <페이징 처리>
    PageResponseDto<ReviewResponseDto> getListByAptIdOrderByCountView(PageRequestDto pageRequestDto, Long aptId);

    // 특정 아파트 리뷰 목록 조회 (평점) <페이징 처리>
    PageResponseDto<ReviewResponseDto> getListByAptIdOrderByRating(PageRequestDto pageRequestDto, Long aptId);

    // 특정 유저 리뷰 조회 <특정 아이디 리뷰 검색해서 가져오기>
    PageResponseDto<ReviewResponseDto> getReviewsByMemberId(PageRequestDto pageRequestDto, Long memberId);

    // 리뷰 삭제


    default ReviewResponseDto entityToDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .author(review.getMember().getUsername())
                .content(review.getContent())
                .aptName(review.getApt().getAptName())
                .rating(review.getRating().getRating())
                .createdAt(Date.from(review.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()))
                .viewCount(review.getViewCount())
                .build();
    }

    default ReviewResponseWithCommentDto entityToCommentDto(Review review) {
        return ReviewResponseWithCommentDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .memberId(review.getMember().getId())
                .author(review.getMember().getUsername())
                .content(review.getContent())
                .aptName(review.getApt().getAptName())
                .rating(review.getRating().getRating())
                .createdAt(Date.from(review.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant()))
                .viewCount(review.getViewCount())
                .build();
    }

    // ---------- 필요없어진 내용 -------------

    // 리뷰 목록 조회 <페이징 처리해서 가져오기>
//    PageResponseDto<ReviewResponseDto> getList(PageRequestDto pageRequestDto);


}
