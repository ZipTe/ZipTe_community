package com.zipte.platform.application.port.in.board;

import com.zipte.platform.application.port.in.dto.request.board.BoardReactionRequest;
import com.zipte.platform.domain.board.BoardReaction;

public interface AddReactionUseCase {

    // 감정 표현 누르기
    BoardReaction addReaction(BoardReactionRequest request);

}
