package org.gdg.zipte.api.controller.review.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.controller.review.review.request.ReviewRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.review.review.ReviewService;
import org.gdg.zipte.api.service.review.review.response.ReviewResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, ReviewRequest reviewRequest) {
        reviewRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(reviewService.create(reviewRequest));
    }

    // 특정 회원의 리뷰 조회
    @GetMapping("/list/member/{memberId}")
    public ApiResponse<PageResponse<ReviewResponse>> getListById(@PathVariable("memberId") Long memberId, PageRequest pageRequest) {
        return ApiResponse.created(reviewService.getReviewsByMemberId(pageRequest, memberId));
    }

    // 특정 아파트 리뷰 조회
    @GetMapping("/list/apt/{aptId}")
    public ApiResponse<PageResponse<ReviewResponse>> getListByAptId(
            @PathVariable("aptId") Long aptId,
            PageRequest pageRequest,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy) {

        switch (orderBy) {
            case "view":
                return ApiResponse.created(reviewService.getListByAptIdOrderByCountView(pageRequest, aptId));
            case "rating":
                return ApiResponse.created(reviewService.getListByAptIdOrderByRating(pageRequest, aptId));
            case "date":
            default:
                return ApiResponse.created(reviewService.getListByAptId(pageRequest, aptId));
        }
    }

    // 리뷰 상세 정보
    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponse> getReviewsWithComments(@PathVariable("reviewId") Long reviewId) {
        return ApiResponse.created(reviewService.getOne(reviewId));
    }

}
