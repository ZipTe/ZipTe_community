package org.gdg.zipte.api.service.board.comment;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte.api.controller.board.comment.request.CommentRequest;
import org.gdg.zipte.api.service.board.comment.response.CommentResponse;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.board.BoardRepository;
import org.gdg.zipte.domain.board.comment.Comment;
import org.gdg.zipte.domain.board.comment.CommentRepository;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    // 댓글 작성
    @Override
    public CommentResponse register(CommentRequest commentRequest) {

        Member member = memberRepository.findById(commentRequest.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));

        Board board = boardRepository.findById(commentRequest.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판이 존재하지 않습니다."));

        // 부모가 없는 경우 null 처리
        Comment parent = null;
        if (commentRequest.getParentId() != null) {
            parent = commentRepository.findById(commentRequest.getParentId()).orElseThrow();
        }

        Comment comment = Comment.of(board, member, parent,commentRequest.getContent());
        Comment saved = commentRepository.save(comment);

        return CommentResponse.from(saved,board.getMember().getUsername());
    }

    // 댓글 조회 (페이징 처리, 대댓글도 같이)
    @Override
    public PageResponse<CommentResponse> findAll(Long boardId, PageRequest pageRequest) {

        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());
        Page<Comment> result = commentRepository.findRootCommentsByBoardId(boardId, pageable);

        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("게시판이 존재하지 않습니다."));
        String author = board.getMember().getUsername();

        List<CommentResponse> dtoList = result.get().map(arr -> {
            // 각 댓글을 CommentResponseWithId로 변환하고, 대댓글 포함
            return CommentResponse.from(arr, author);
        }).toList();

        long total = result.getTotalElements();
        return new PageResponse<>(dtoList, pageRequest, total);
    }
}
