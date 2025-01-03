package org.gdg.zipte.api.controller.review.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.controller.review.review.request.ReviewRequest;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.api.service.review.review.ReviewService;
import org.gdg.zipte.api.service.review.review.response.ReviewResponseDto;
import org.gdg.zipte.api.service.review.review.response.ReviewResponseWithCommentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResponseDto> create(ReviewRequest reviewRequest) {
        return ApiResponse.created(reviewService.register(reviewRequest));
    }

    // 특정 회원의 리뷰 조회
    @GetMapping("/list/member/{memberId}")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getListById(@PathVariable("memberId") Long memberId, PageRequestDto pageRequestDto) {
        return ApiResponse.created(reviewService.getReviewsByMemberId(pageRequestDto, memberId));
    }

    // 특정 아파트 리뷰 조회
    @GetMapping("/list/apt/{aptId}")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getListByAptId(
            @PathVariable("aptId") Long aptId,
            PageRequestDto pageRequestDto,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy) {

        switch (orderBy) {
            case "view":
                return ApiResponse.created(reviewService.getListByAptIdOrderByCountView(pageRequestDto, aptId));
            case "rating":
                return ApiResponse.created(reviewService.getListByAptIdOrderByRating(pageRequestDto, aptId));
            case "date":
            default:
                return ApiResponse.created(reviewService.getListByAptId(pageRequestDto, aptId));
        }
    }

    // 리뷰 상세 정보
    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponseWithCommentDto> getReviewsWithComments(@PathVariable("reviewId") Long reviewId) {
        return ApiResponse.created(reviewService.getOne(reviewId));
    }

}
