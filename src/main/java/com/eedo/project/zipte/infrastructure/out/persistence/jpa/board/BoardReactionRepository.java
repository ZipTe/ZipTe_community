package com.eedo.project.zipte.infrastructure.out.persistence.jpa.board;

import com.eedo.project.zipte.domain.board.Board;
import com.eedo.project.zipte.domain.board.BoardReaction;
import com.eedo.project.zipte.domain.board.UserReaction;
import com.eedo.project.zipte.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionRepository extends JpaRepository<BoardReaction, Long> {

    Optional<BoardReaction> findByBoardAndMember(Board board, Member member);
    Optional<BoardReaction> findByBoardAndMemberAndReactionType(Board board, Member member, UserReaction reactionType);
}
