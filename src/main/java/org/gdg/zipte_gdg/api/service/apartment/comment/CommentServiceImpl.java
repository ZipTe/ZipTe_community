package org.gdg.zipte_gdg.api.service.apartment.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.apartment.comment.request.CommentRequestDto;
import org.gdg.zipte_gdg.api.service.apartment.comment.response.CommentResponseDto;
import org.gdg.zipte_gdg.domain.apartment.comment.Comment;
import org.gdg.zipte_gdg.domain.apartment.comment.CommentRepository;
import org.gdg.zipte_gdg.domain.user.member.Member;
import org.gdg.zipte_gdg.domain.user.member.MemberRepository;
import org.gdg.zipte_gdg.domain.apartment.review.Review;
import org.gdg.zipte_gdg.domain.apartment.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResponseDto register(CommentRequestDto commentRequestDto) {

        Optional<Member> byId = memberRepository.findById(commentRequestDto.getMemberId());
        Member member = byId.orElseThrow();

        Optional<Review> byId1 = reviewRepository.findById(commentRequestDto.getReviewId());
        Review review = byId1.orElseThrow();

        Comment comment = Comment.addNewComment(review, member, commentRequestDto.getContent());
        Comment saved = commentRepository.save(comment);

        return entityToDto(saved);
    }

}
