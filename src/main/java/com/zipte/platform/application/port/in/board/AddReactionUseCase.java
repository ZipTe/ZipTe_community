package com.zipte.platform.application.port.in.board;

import com.zipte.platform.adapter.in.api.dto.request.board.BoardReactionRequest;
import com.zipte.platform.adapter.in.api.dto.response.BoardReactionResponse;

public interface AddReactionUseCase {

    // 감정 표현 누르기
    BoardReactionResponse create(BoardReactionRequest request);

}
