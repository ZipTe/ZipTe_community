package org.gdg.zipte.api.service.board.commentLike;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.commentLike.request.CommentReactionRequest;
import org.gdg.zipte.api.service.board.commentLike.response.CommentReactionResponse;
import org.gdg.zipte.domain.board.comment.Comment;
import org.gdg.zipte.domain.board.comment.CommentRepository;
import org.gdg.zipte.domain.board.like.CommentReaction;
import org.gdg.zipte.domain.board.like.CommentReactionRepository;
import org.gdg.zipte.domain.board.like.UserReaction;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentReactionServiceImpl implements CommentReactionService {

    private final CommentReactionRepository commentReactionRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Override
    public CommentReactionResponse create(CommentReactionRequest request) {
        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 동일한 회원이 동일한 게시글에 이미 반응을 남겼는지 확인
        commentReactionRepository.findByCommentAndMember(comment, member).ifPresent(reaction -> {
            throw new IllegalStateException("Reaction already exists");
        });

        // 새로운 반응 생성 및 저장
        CommentReaction reaction = CommentReaction.of(comment, member, request.getReactionType());
        commentReactionRepository.save(reaction);

        return CommentReactionResponse.from(reaction);
    }

    @Override
    public CommentReactionResponse delete(CommentReactionRequest request) {
        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        // 요청된 반응 유형 가져오기
        UserReaction reactionType = request.getReactionType();

        // 요청된 반응 찾기
        CommentReaction reaction = commentReactionRepository.findByCommentAndMemberAndReactionType(comment, member, reactionType)
                .orElseThrow(() -> new IllegalArgumentException("Specified reaction not found"));

        // 반응 삭제
        commentReactionRepository.delete(reaction);

        return CommentReactionResponse.from(reaction);
    }
}
