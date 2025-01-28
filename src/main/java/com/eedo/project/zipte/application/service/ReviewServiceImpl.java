package com.eedo.project.zipte.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.application.port.in.ReviewImageService;
import com.eedo.project.zipte.application.port.in.ReviewService;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.zipte.representation.request.review.ReviewRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.ReviewResponse;
import com.eedo.project.zipte.domain.apt.Apt;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.apt.AptRepository;
import com.eedo.project.zipte.domain.user.Member;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.user.MemberRepository;
import com.eedo.project.zipte.domain.review.Rating;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.review.RatingRepository;
import com.eedo.project.zipte.domain.review.Review;
import com.eedo.project.zipte.domain.review.ReviewImage;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.review.ReviewRepository;
import org.springframework.data.domain.Page;
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

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()-> new NoSuchElementException("유저가 존재하지 않습니다."));

        Apt apt = aptRepository.findById(request.getAptId())
                .orElseThrow(()-> new NoSuchElementException("아파트가 존재하지 않습니다."));
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

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new NoSuchElementException("리뷰가 존재하지 않습니다."));

        // API 호출할 때마다 조회수 증가
        review.addCount();
        reviewRepository.save(review);

        ReviewResponse response = ReviewResponse.from(review);
        response.setUploadFileNames(review.getReviewImages().stream().map(ReviewImage::getFileName).collect(Collectors.toList()));

        return response;
    }
    //

    @Override
    public PageResponse<ReviewResponse> getListByAptId(PageRequest pageRequest, Long aptId) {

        // 멤버 존재 여부에 따른 예외처리
        aptRepository.findById(aptId) .orElseThrow(() -> new EntityNotFoundException("해당 아파트가 존재하지 않습니다."));


        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage()-1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Object[]> result = reviewRepository.selectListbyAptId(aptId, pageable);


        return getReviewResponsePageResponseDto(pageRequest, result);
    }

    @Override
    public PageResponse<ReviewResponse> getListByAptIdOrderByCountView(PageRequest pageRequest, Long aptId) {

        // 멤버 존재 여부에 따른 예외처리
        aptRepository.findById(aptId) .orElseThrow(() -> new EntityNotFoundException("해당 아파트가 존재하지 않습니다."));

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage()-1, pageRequest.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.selectListbyAptIdOrderByCountViewDesc(aptId, pageable);

        return getReviewResponsePageResponseDto(pageRequest, result);
    }

    @Override
    public PageResponse<ReviewResponse> getListByAptIdOrderByRating(PageRequest pageRequest, Long aptId) {

        // 멤버 존재 여부에 따른 예외처리
        aptRepository.findById(aptId) .orElseThrow(() -> new EntityNotFoundException("해당 아파트가 존재하지 않습니다."));

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage()-1, pageRequest.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.selectListbyAptIdOrderByRatingDesc(aptId, pageable);


        return getReviewResponsePageResponseDto(pageRequest, result);
    }


    @Override
    public PageResponse<ReviewResponse> getReviewsByMemberId(PageRequest pageRequest, Long memberId) {

        // 멤버 존재 여부에 따른 예외처리
        memberRepository.findById(memberId) .orElseThrow(() -> new EntityNotFoundException("해당 멤버가 존재하지 않습니다."));

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());

        Page<Object[]> result = reviewRepository.findReviewsByMemberId(memberId, pageable);

        return getReviewResponsePageResponseDto(pageRequest, result);
    }

    // extract
    private PageResponse<ReviewResponse> getReviewResponsePageResponseDto(PageRequest pageRequest, Page<Object[]> result) {
        List<ReviewResponse> dtoList = result.get().map(arr -> {
            Review review = (Review) arr[0];
            ReviewImage reviewImage = (ReviewImage) arr[1];

            String imageStr = (reviewImage != null) ? reviewImage.getFileName() : "No image found";
            ReviewResponse dto = ReviewResponse.from(review);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();

        long total = result.getTotalElements();

        return new PageResponse<>(dtoList, pageRequest, total);
    }


}
