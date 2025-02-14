package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.BoardReaction;

public interface SaveBoardReactionPort {

    BoardReaction saveBoardReaction(BoardReaction boardReaction);

}
