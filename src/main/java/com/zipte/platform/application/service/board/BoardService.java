package com.zipte.platform.application.service.board;

import com.zipte.platform.application.port.in.board.CreateBoardUseCase;
import com.zipte.platform.application.port.in.board.GetBoardInfoUseCase;
import com.zipte.platform.application.port.in.board.RemoveBoardUseCase;
import com.zipte.platform.application.port.out.*;
import com.zipte.platform.domain.board.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.zipte.platform.adapter.in.api.dto.request.board.BoardRequest;
import com.zipte.core.common.page.request.PageRequest;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class BoardService implements CreateBoardUseCase, GetBoardInfoUseCase, RemoveBoardUseCase {

    private final SaveBoardPort saveBoardPort;
    private final LoadBoardPort loadBoardPort;
    private final LoadMemberPort loadMemberPort;
    private final CategoryPort categoryPort;
    private final RemoveBoardPort removeBoardPort;

    // 생성하기
    @Override
    public Board createBoard(BoardRequest request) {

        // 유저 생성
        Member member = loadMemberPort.loadUser(request.getMemberId())
                .orElseThrow(()-> new EntityNotFoundException("해당 멤버가 존재하지 않습니다."));

        // 게시물 생성
        BoardSnippet snippet = BoardSnippet.of(request.getTitle(), request.getContent(), "링크", LocalDateTime.now());
        BoardStatistics statistics = BoardStatistics.of();

        // 카테고리와 연결
        List<Category> categories = categoryPort.loadAllByCategoryId(request.getCategoryIds());

        return saveBoardPort.saveBoard(Board.of(member, snippet, statistics, categories));

    }

    @Override
    public Page<Board> getByCategoryId(Long categoryId, PageRequest pageRequest) {

        // 1. ID 목록을 이용해 게시글 조회
        Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getPage() - 1, pageRequest.getSize(), Sort.by("id").descending());

        // 2. 페이징 처리하기
        return loadBoardPort.loadByCategoryId(categoryId, pageable);
    }

    @Override
    public Board getOneInfo(Long boardId) {

        // 아이템 정보 가져오기
        Board board = loadBoardPort.loadBoardById(boardId)
                .orElseThrow(()-> new EntityNotFoundException("게시판이 존재하지 않습니다."));

        // 조회수 처리
        board.getStatistics().addViewCount();
        return saveBoardPort.saveBoard(board);
    }


    @Override
    public void removeBoard(Long boardId) {
        removeBoardPort.removeBoard(boardId);
    }
}
