package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.board.BoardReactionJpaEntity;
import com.zipte.platform.adapter.out.jpa.board.repository.BoardReactionJpaRepository;
import com.zipte.platform.application.port.out.LoadBoardReactionPort;
import com.zipte.platform.application.port.out.RemoveBoardReactionPort;
import com.zipte.platform.application.port.out.SaveBoardReactionPort;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BoardReactionPersistenceAdapter implements LoadBoardReactionPort, SaveBoardReactionPort, RemoveBoardReactionPort {

    private final BoardReactionJpaRepository repository;

    @Override
    public BoardReaction saveBoardReaction(BoardReaction boardReaction) {
        var result = BoardReactionJpaEntity.from(boardReaction);

        return repository.save(result).toDomain();
    }

    @Override
    public Optional<BoardReaction> loadBoardReaction(Long boardId, Long memberId) {

        return repository.findByBoardIdAndMemberId(boardId, memberId)
                .map(BoardReactionJpaEntity::toDomain);
    }

    @Override
    public Optional<BoardReaction> loadBoardReactionByType(Long boardId, Long memberId, UserReaction reactionType) {

        return repository.findByBoardIdAndMemberIdAndReactionType(boardId, memberId, reactionType)
                .map(BoardReactionJpaEntity::toDomain);
    }

    @Override
    public void removeBoardReaction(BoardReaction boardReaction) {

        repository.deleteById(boardReaction.getId());
    }
}
