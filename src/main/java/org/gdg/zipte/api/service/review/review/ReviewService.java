package org.gdg.zipte.api.service.review.review;

import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.api.controller.review.review.request.ReviewRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.review.review.response.ReviewResponse;

public interface ReviewService {

    // 리뷰 등록
    ReviewResponse create(ReviewRequest reviewRequest);

    // 리뷰 수정

    // 리뷰 조회
    ReviewResponse getOne(Long id);

    // 리뷰 삭제


    // 특정 아파트 리뷰 목록 조회 (최신) <페이징 처리>
    PageResponse<ReviewResponse> getListByAptId(PageRequest pageRequest, Long aptId);

    // 특정 아파트 리뷰 목록 조회 (조회수) <페이징 처리>
    PageResponse<ReviewResponse> getListByAptIdOrderByCountView(PageRequest pageRequest, Long aptId);

    // 특정 아파트 리뷰 목록 조회 (평점) <페이징 처리>
    PageResponse<ReviewResponse> getListByAptIdOrderByRating(PageRequest pageRequest, Long aptId);

    // 특정 유저 리뷰 조회 <특정 아이디 리뷰 검색해서 가져오기>
    PageResponse<ReviewResponse> getReviewsByMemberId(PageRequest pageRequest, Long memberId);

}
