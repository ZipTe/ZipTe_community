package org.gdg.zipte.api.service.board.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte.api.controller.board.comment.request.CommentRequest;
import org.gdg.zipte.api.service.board.comment.response.CommentResponse;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.board.BoardRepository;
import org.gdg.zipte.domain.board.comment.Comment;
import org.gdg.zipte.domain.board.comment.CommentRepository;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    @Override
    public CommentResponse register(CommentRequest commentRequest) {

        Member member = memberRepository.findById(commentRequest.getMemberId()).orElseThrow();
        Board board = boardRepository.findById(commentRequest.getBoardId()).orElseThrow();

        Comment comment = Comment.of(board, member, commentRequest.getContent());
        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved);
    }

}
