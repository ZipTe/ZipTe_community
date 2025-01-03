package org.gdg.zipte.api.service.review.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte.api.controller.review.comment.request.CommentRequest;
import org.gdg.zipte.api.service.review.comment.response.CommentResponse;
import org.gdg.zipte.domain.review.comment.Comment;
import org.gdg.zipte.domain.review.comment.CommentRepository;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.gdg.zipte.domain.review.review.Review;
import org.gdg.zipte.domain.review.review.ReviewRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public CommentResponse register(CommentRequest commentRequest) {

        Member member = memberRepository.findById(commentRequest.getMemberId()).orElseThrow();
        Review review = reviewRepository.findById(commentRequest.getReviewId()).orElseThrow();

        Comment comment = Comment.of(review, member, commentRequest.getContent());
        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }

}
