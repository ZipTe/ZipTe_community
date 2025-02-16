package com.zipte.platform.application.port.in.board;

import com.zipte.platform.application.port.in.dto.request.board.BoardReactionRequest;

public interface RemoveReactionUseCase {

    // 감정 표현 지우기
    void removeReaction(BoardReactionRequest request);


}
