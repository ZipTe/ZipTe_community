package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;
import com.zipte.platform.domain.user.Member;

import java.util.Optional;

public interface LoadBoardReactionPort {

    Optional<BoardReaction> loadBoardReaction(Long boardId, Member member);
    Optional<BoardReaction> loadBoardReactionByType(Long boardId, Member member, UserReaction reactionType);
}
