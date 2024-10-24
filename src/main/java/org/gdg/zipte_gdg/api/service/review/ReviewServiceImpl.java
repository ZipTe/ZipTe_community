package org.gdg.zipte_gdg.api.service.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.review.response.ReviewResponseDto;
import org.gdg.zipte_gdg.domain.review.Review;
import org.gdg.zipte_gdg.domain.review.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public PageResponseDto<ReviewResponseDto> getList(PageRequestDto pageRequestDto) {
        log.info("=== getList ===");

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Review> result = reviewRepository.findAll(pageable);

        log.info(result);

        // Review 엔티티를 ReviewResponseDto로 변환
        List<ReviewResponseDto> dtoList = result.stream()
                .map(this::entityToDto)  // entityToDto 메서드 사용
                .collect(Collectors.toList());

        long total = result.getTotalElements();

        return new PageResponseDto<ReviewResponseDto>(dtoList, pageRequestDto, total);
    }

    @Override
    public PageResponseDto<ReviewResponseDto> getReviewsByMemberId(PageRequestDto pageRequestDto, Long memberId) {
        log.info("=== getListById ===");

        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Review> result = reviewRepository.findReviewsByMemberId(memberId, pageable);

        log.info(result);

        // Review 엔티티를 ReviewResponseDto로 변환
        List<ReviewResponseDto> dtoList = result.stream()
                .map(this::entityToDto)  // entityToDto 메서드 사용
                .collect(Collectors.toList());

        long total = result.getTotalElements();

        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }


    @Override
    public Long register(ReviewResponseDto reviewResponseDto) {
        return 0L;
    }
}
