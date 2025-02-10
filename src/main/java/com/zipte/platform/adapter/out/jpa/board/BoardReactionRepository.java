package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionRepository extends JpaRepository<BoardReaction, Long> {

    Optional<BoardReaction> findByBoardAndMember(Board board, Member member);
    Optional<BoardReaction> findByBoardAndMemberAndReactionType(Board board, Member member, UserReaction reactionType);
}
