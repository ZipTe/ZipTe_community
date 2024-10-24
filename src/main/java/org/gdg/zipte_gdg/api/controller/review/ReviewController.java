package org.gdg.zipte_gdg.api.controller.review;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.review.ReviewService;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/list")
    public PageResponseDto<ReviewResponseDto> getList(PageRequestDto pageRequestDto) {
        return reviewService.getList(pageRequestDto);
    }

    @GetMapping("/list/{memberId}")
    public PageResponseDto<ReviewResponseDto> getListbyId(@PathVariable("memberId") Long memberId, PageRequestDto pageRequestDto) {
        return reviewService.getReviewsByMemberId(pageRequestDto, memberId);
    }

}
