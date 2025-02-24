package com.zipte.platform.application.service.board;

import com.zipte.platform.application.port.in.board.CreateBoardUseCase;
import com.zipte.platform.application.port.in.board.GetBoardInfoUseCase;
import com.zipte.platform.application.port.in.board.RemoveBoardUseCase;
import com.zipte.platform.application.port.in.dto.request.board.BoardRequest;
import com.zipte.platform.application.port.out.*;
import com.zipte.platform.domain.board.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

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

    @Override
    public Board createBoard(BoardRequest request) {
//        if (!loadMemberPort.existsById(request.getMemberId())) {
//            throw new NoSuchElementException("해당 ID의 멤버가 존재하지 않습니다: " + request.getMemberId());
//        }

        // 게시물 생성
        BoardSnippet snippet = BoardSnippet.of(request.getTitle(), request.getContent(), "링크", LocalDateTime.now());
        BoardStatistics statistics = BoardStatistics.of();

        // 카테고리와 연결
        Category category = categoryPort.loadCategory(request.getCategoryId())
                .orElseThrow(() -> new NoSuchElementException("카테고리가 존재하지 않습니다"));

        Board board = Board.of(request.getMemberId(), snippet, statistics, category);
        return saveBoardPort.saveBoard(board);
    }


    @Override
    public Page<Board> getByCategoryId(Long categoryId, Pageable pageable) {
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
