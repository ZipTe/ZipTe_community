package com.zipte.platform.adapter.out;

import com.zipte.platform.adapter.out.jpa.board.BoardReactionJpaEntity;
import com.zipte.platform.adapter.out.jpa.board.repository.BoardReactionJpaRepository;
import com.zipte.platform.application.port.out.LoadBoardReactionPort;
import com.zipte.platform.application.port.out.RemoveBoardReactionPort;
import com.zipte.platform.application.port.out.SaveBoardReactionPort;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BoardReactionPersistenceAdapter implements LoadBoardReactionPort, SaveBoardReactionPort, RemoveBoardReactionPort {

    private final BoardReactionJpaRepository repository;

    @Override
    public BoardReaction saveBoardReaction(BoardReaction boardReaction) {
        var result = BoardReactionJpaEntity.from(boardReaction);

        return repository.save(result).toDomain();
    }

    @Override
    public Optional<BoardReaction> loadBoardReaction(Long boardId, Member member) {

        return repository.findByBoardIdAndMember(boardId, member)
                .map(BoardReactionJpaEntity::toDomain);
    }

    @Override
    public Optional<BoardReaction> loadBoardReactionByType(Long boardId, Member member, UserReaction reactionType) {

        return repository.findByBoardIdAndMemberAndReactionType(boardId, member, reactionType)
                .map(BoardReactionJpaEntity::toDomain);
    }

    @Override
    public void removeBoardReaction(BoardReaction boardReaction) {

        repository.deleteById(boardReaction.getId());
    }
}
