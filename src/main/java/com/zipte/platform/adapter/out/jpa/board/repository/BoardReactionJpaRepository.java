package com.zipte.platform.adapter.out.jpa.board.repository;

import com.zipte.platform.adapter.out.jpa.board.BoardReactionJpaEntity;
import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionJpaRepository extends JpaRepository<BoardReaction, Long> {

    Optional<BoardReactionJpaEntity> findByBoardAndMember(Board board, Member member);
    Optional<BoardReactionJpaEntity> findByBoardAndMemberAndReactionType(Board board, Member member, UserReaction reactionType);
}
