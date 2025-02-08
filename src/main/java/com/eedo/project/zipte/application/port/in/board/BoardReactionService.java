package com.eedo.project.zipte.application.port.in.board;

import com.eedo.project.zipte.representation.request.board.BoardReactionRequest;
import com.eedo.project.zipte.representation.response.BoardReactionResponse;

public interface BoardReactionService {

    // 추가하기
    BoardReactionResponse create(BoardReactionRequest request);

    // 취소하기
    BoardReactionResponse delete(BoardReactionRequest request);
}
