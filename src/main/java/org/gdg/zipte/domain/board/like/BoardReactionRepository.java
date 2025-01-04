package org.gdg.zipte.domain.board.like;

import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.user.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardReactionRepository extends JpaRepository<BoardReaction, Long> {

    Optional<BoardReaction> findByBoardAndMember(Board board, Member member);
    Optional<BoardReaction> findByBoardAndMemberAndReactionType(Board board, Member member, UserReaction reactionType);
}
