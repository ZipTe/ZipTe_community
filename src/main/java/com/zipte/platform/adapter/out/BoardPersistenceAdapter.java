package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.board.BoardJpaEntity;
import com.zipte.platform.adapter.out.jpa.board.repository.BoardCategoryJpaRepository;
import com.zipte.platform.adapter.out.jpa.board.repository.BoardJpaRepository;
import com.zipte.platform.application.port.out.LoadBoardPort;
import com.zipte.platform.application.port.out.RemoveBoardPort;
import com.zipte.platform.application.port.out.SaveBoardPort;
import com.zipte.platform.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements SaveBoardPort, LoadBoardPort, RemoveBoardPort {

    private final BoardJpaRepository repository;
    private final BoardCategoryJpaRepository boardCategoryRepository;

    @Override
    public Board save(Board board) {
        var boardJpaEntity = BoardJpaEntity.from(board);

        return repository.save(boardJpaEntity)
                .toDomain();
    }

    @Override
    public Optional<Board> findById(Long boardId) {
        return repository.findById(boardId)
                .map(BoardJpaEntity::toDomain);
    }

    @Override
    public Page<Board> findByCategoryId(Long categoryId, Pageable pageable) {
        Page<BoardJpaEntity> result = boardCategoryRepository.findBoardJpaEntitiesByCategoryId(categoryId, pageable);

        List<Board> boards = result.stream()
                .map(BoardJpaEntity::toDomain)
                .toList();

        return new PageImpl<>(boards, pageable, result.getTotalElements());
    }


    @Override
    public void removeBoard(Long boardId) {
        repository.deleteById(boardId);
    }
}
