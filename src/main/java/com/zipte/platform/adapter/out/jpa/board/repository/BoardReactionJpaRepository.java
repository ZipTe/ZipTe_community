package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.BoardReactionJpaEntity;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionJpaRepository extends JpaRepository<BoardReactionJpaEntity, Long> {

    Optional<BoardReactionJpaEntity> findByBoardIdAndMember(Long boardId, Member member);
    Optional<BoardReactionJpaEntity> findByBoardIdAndMemberAndReactionType(Long boardId, Member member, UserReaction reactionType);

}
