package org.gdg.zipte.api.service.review.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.api.controller.review.review.request.ReviewRequest;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.api.service.review.review.response.ReviewResponse;
import org.gdg.zipte.domain.apt.apt.Apt;
import org.gdg.zipte.domain.apt.apt.AptRepository;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.gdg.zipte.domain.review.rating.Rating;
import org.gdg.zipte.domain.review.rating.RatingRepository;
import org.gdg.zipte.domain.review.review.Review;
import org.gdg.zipte.domain.review.review.ReviewImage;
import org.gdg.zipte.domain.review.review.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewImageService reviewImageService;
    private final AptRepository aptRepository;
    private final RatingRepository ratingRepository;

    // 등록
    @Override
    public ReviewResponse create(ReviewRequest request) {

        Member member = memberRepository.findById(request.getMemberId()).orElseThrow();
        Apt apt = aptRepository.findById(request.getAptId()).orElseThrow();
        Rating rating = getRating(request, member, apt);

        // 리뷰 생성
        Review result = Review.of(member, apt, request.getTitle(), request.getContent(), rating);
        Review review = reviewRepository.save(result);

        // DTO생성
        ReviewResponse reviewResponse = ReviewResponse.from(review);

        List<String> uploads = reviewImageService.saveFiles(review, request.getFiles());
        reviewResponse.setUploadFileNames(uploads);

        return reviewResponse;
    }

    private Rating getRating(ReviewRequest request, Member member, Apt apt) {
        Rating rating = Rating.of(member, apt, request.getTransport(), request.getEnvironment(), request.getApartmentManagement(), request.getLivingEnvironment());
        return ratingRepository.save(rating);
    }

    // 조회
    @Override
    public ReviewResponse getOne(Long reviewId) {

        Review review = reviewRepository.findById(reviewId).orElseThrow();

        // API 호출할 때마다 조회수 증가
        review.addCount();
        reviewRepository.save(review);

        ReviewResponse response = ReviewResponse.from(review);
        response.setUploadFileNames(review.getReviewImages().stream().map(ReviewImage::getFileName).collect(Collectors.toList()));

        return response;
    }
    //

    @Override
    public PageResponseDto<ReviewResponse> getListByAptId(PageRequestDto pageRequestDto, Long aptId) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());
        Page<Object[]> result = reviewRepository.selectListbyAptId(aptId, pageable);


        return getReviewResponsePageResponseDto(pageRequestDto, result);
    }

    @Override
    public PageResponseDto<ReviewResponse> getListByAptIdOrderByCountView(PageRequestDto pageRequestDto, Long aptId) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.selectListbyAptIdOrderByCountViewDesc(aptId, pageable);

        return getReviewResponsePageResponseDto(pageRequestDto, result);
    }

    @Override
    public PageResponseDto<ReviewResponse> getListByAptIdOrderByRating(PageRequestDto pageRequestDto, Long aptId) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage()-1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.selectListbyAptIdOrderByRatingDesc(aptId, pageable);


        return getReviewResponsePageResponseDto(pageRequestDto, result);
    }


    @Override
    public PageResponseDto<ReviewResponse> getReviewsByMemberId(PageRequestDto pageRequestDto, Long memberId) {

        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1, pageRequestDto.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.findReviewsByMemberId(memberId, pageable);

        return getReviewResponsePageResponseDto(pageRequestDto, result);
    }

    // extract
    private PageResponseDto<ReviewResponse> getReviewResponsePageResponseDto(PageRequestDto pageRequestDto, Page<Object[]> result) {
        List<ReviewResponse> dtoList = result.get().map(arr -> {
            Review review = (Review) arr[0];
            ReviewImage reviewImage = (ReviewImage) arr[1];

            String imageStr = (reviewImage != null) ? reviewImage.getFileName() : "No image found";
            ReviewResponse dto = ReviewResponse.from(review);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();

        return new PageResponseDto<>(dtoList, pageRequestDto, total);
    }


}
