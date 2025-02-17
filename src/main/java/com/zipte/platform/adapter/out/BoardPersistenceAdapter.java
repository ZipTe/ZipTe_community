package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.board.BoardJpaEntity;
import com.zipte.platform.adapter.out.jpa.board.repository.BoardJpaRepository;
import com.zipte.platform.application.port.out.LoadBoardPort;
import com.zipte.platform.application.port.out.RemoveBoardPort;
import com.zipte.platform.application.port.out.SaveBoardPort;
import com.zipte.platform.domain.board.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardPersistenceAdapter implements SaveBoardPort, LoadBoardPort, RemoveBoardPort {

    private final BoardJpaRepository repository;

    @Override
    public Board saveBoard(Board board) {
        var boardJpaEntity = BoardJpaEntity.from(board);

        return repository.save(boardJpaEntity)
                .toDomain();
    }

    @Override
    public Optional<Board> loadBoardById(Long boardId) {
        return repository.findById(boardId)
                .map(BoardJpaEntity::toDomain);
    }
//
    @Override
    public Page<Board> loadByCategoryId(Long categoryId, Pageable pageable) {
        Page<BoardJpaEntity> result = repository.findBoardJpaEntitiesByCategoryId(categoryId, pageable);

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
