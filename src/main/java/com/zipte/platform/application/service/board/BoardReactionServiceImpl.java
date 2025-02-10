package com.zipte.platform.application.service.board;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import com.zipte.platform.adapter.in.api.dto.request.board.BoardReactionRequest;
import com.zipte.platform.application.port.in.board.BoardReactionService;
import com.zipte.platform.adapter.in.api.dto.response.BoardReactionResponse;
import com.zipte.platform.domain.board.Board;
import com.eedo.project.zipte.adapter.out.persistence.jpa.board.BoardRepository;
import com.zipte.platform.domain.board.BoardReaction;
import com.eedo.project.zipte.adapter.out.persistence.jpa.board.BoardReactionRepository;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import com.eedo.project.zipte.adapter.out.persistence.jpa.user.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardReactionServiceImpl implements BoardReactionService {

    private final BoardReactionRepository boardReactionRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    @Override
    public BoardReactionResponse create(BoardReactionRequest request) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판이 존재하지 않습니다"));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));

        // 동일한 회원이 동일한 게시글에 이미 반응을 남겼는지 확인
        boardReactionRepository.findByBoardAndMember(board, member).ifPresent(reaction -> {
            throw new IllegalStateException("리액션이 이미 존재합니다.");
        });

        // 새로운 반응 생성 및 저장
        BoardReaction reaction = BoardReaction.of(board, member, request.getReactionType());
        boardReactionRepository.save(reaction);

        return BoardReactionResponse.from(reaction);
    }

    @Override
    public BoardReactionResponse delete(BoardReactionRequest request) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new EntityNotFoundException("해당 게시판이 존재하지 않습니다."));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new EntityNotFoundException("해당 회원이 존재하지 않습니다."));

        // 요청된 반응 유형 가져오기
        UserReaction reactionType = request.getReactionType();

        // 요청된 반응 찾기
        BoardReaction reaction = boardReactionRepository.findByBoardAndMemberAndReactionType(board, member, reactionType)
                .orElseThrow(() -> new IllegalArgumentException("리액션을 삭제할 수 없습니다."));

        // 반응 삭제
        boardReactionRepository.delete(reaction);

        return BoardReactionResponse.from(reaction);
    }
}
