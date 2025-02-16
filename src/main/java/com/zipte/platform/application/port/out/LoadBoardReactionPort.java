package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.BoardReaction;
import com.zipte.platform.domain.board.UserReaction;

import java.util.Optional;

public interface LoadBoardReactionPort {

    Optional<BoardReaction> loadBoardReaction(Long boardId, Long memberId);
    Optional<BoardReaction> loadBoardReactionByType(Long boardId, Long memberId, UserReaction reactionType);
}
