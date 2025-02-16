package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.BoardReactionJpaEntity;
import com.zipte.platform.domain.board.UserReaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionJpaRepository extends JpaRepository<BoardReactionJpaEntity, Long> {

    Optional<BoardReactionJpaEntity> findByBoardIdAndMemberId(Long boardId, Long memberId);
    Optional<BoardReactionJpaEntity> findByBoardIdAndMemberIdAndReactionType(Long boardId, Long memberId, UserReaction reactionType);

}
