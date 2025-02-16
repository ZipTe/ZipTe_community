package com.zipte.platform.adapter.in.api;

import com.zipte.platform.application.port.in.dto.request.review.ReviewRequest;
import com.zipte.platform.application.port.in.review.CreateReviewUseCase;
import com.zipte.platform.application.port.in.review.GetReviewUseCase;
import com.zipte.platform.application.port.in.review.RemoveReviewUseCase;
import com.zipte.platform.domain.review.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.zipte.core.common.page.request.PageRequest;
import com.zipte.core.common.ApiResponse;
import com.zipte.core.common.page.response.PageResponse;
import com.zipte.platform.adapter.in.api.dto.response.ReviewResponse;
import com.zipte.core.security.oauth.domain.PrincipalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
@Log4j2
public class ReviewController {

    private final CreateReviewUseCase createService;
    private final GetReviewUseCase getService;
    private final RemoveReviewUseCase removeService;

    // 리뷰 작성
    @PostMapping
    public ApiResponse<ReviewResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, ReviewRequest reviewRequest) {

        reviewRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(ReviewResponse.from(createService.createReview(reviewRequest)));
    }

    // 특정 회원의 리뷰 조회
    @GetMapping("/list/member/myReview")
    public ApiResponse<PageResponse<ReviewResponse>> getMyReview(@AuthenticationPrincipal PrincipalDetails principalDetails, PageRequest pageRequest) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Review> result = getService.getReviewsByMember(principalDetails.getId(), pageable);

        List<ReviewResponse> dtolist = ReviewResponse.from(result.getContent());

        return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));
    }

    // 특정 회원의 리뷰 조회
    @GetMapping("/list/member/{memberId}")
    public ApiResponse<PageResponse<ReviewResponse>> getReviewByMemberId(@PathVariable("memberId") Long memberId, PageRequest pageRequest) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());

        Page<Review> result = getService.getReviewsByMember(memberId, pageable);
        List<ReviewResponse> dtolist = ReviewResponse.from(result.getContent());


        return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));

    }

    // 특정 아파트 리뷰 조회
    @GetMapping("/list/apt/{aptId}")
    public ApiResponse<PageResponse<ReviewResponse>> getListByAptId(
            @PathVariable("aptId") String aptId,
            PageRequest pageRequest,
            @RequestParam(value = "orderBy", defaultValue = "date") String orderBy) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());


        switch (orderBy) {
            case "rating":
                Page<Review> result = getService.getReviewsByRating(aptId, pageable);
                List<ReviewResponse> dtolist = ReviewResponse.from(result.getContent());

                return ApiResponse.ok(new PageResponse<>(dtolist, pageRequest, result.getTotalElements()));

            case "date":
            default:
                Page<Review> result2 = getService.getReviews(aptId, pageable);
                List<ReviewResponse> dtolist2 = ReviewResponse.from(result2.getContent());

                return ApiResponse.ok(new PageResponse<>(dtolist2, pageRequest, result2.getTotalElements()));

        }
    }

    // 리뷰 상세 정보
    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponse> getReviewsWithComments(@PathVariable("reviewId") Long reviewId) {

        Review review = getService.getReview(reviewId)
                .orElseThrow(() -> new NoSuchElementException("해당 리뷰가 존재하지 않습니다."));

        return ApiResponse.ok(ReviewResponse.from(review));
    }

}
