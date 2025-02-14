package com.zipte.platform.application.port.in.board;

import com.zipte.platform.adapter.in.api.dto.request.board.BoardReactionRequest;
import com.zipte.platform.adapter.in.api.dto.response.BoardReactionResponse;
import com.zipte.platform.domain.board.BoardReaction;

public interface RemoveReactionUseCase {

    // 감정 표현 지우기
    BoardReaction delete(BoardReactionRequest request);

}
