package org.gdg.zipte_gdg.api.controller.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.controller.review.request.ReviewRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.review.ReviewService;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseDto;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseWithCommentDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ApiResponse<ReviewResponseDto> create(@RequestBody ReviewRequestDto reviewRequestDto) {
        return ApiResponse.created(reviewService.register(reviewRequestDto));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(reviewService.getList(pageRequestDto));
    }

    @GetMapping("/list/{memberId}")
    public ApiResponse<PageResponseDto<ReviewResponseDto>> getListbyId(@PathVariable("memberId") Long memberId, PageRequestDto pageRequestDto) {
        return ApiResponse.created(reviewService.getReviewsByMemberId(pageRequestDto, memberId));
    }

    @GetMapping("/{reviewId}")
    public ApiResponse<ReviewResponseWithCommentDto> getReviewsWithComments(@PathVariable("reviewId") Long reviewId) {
        log.info("Logs" + reviewId);
        return ApiResponse.created(reviewService.getOne(reviewId));
    }
}
