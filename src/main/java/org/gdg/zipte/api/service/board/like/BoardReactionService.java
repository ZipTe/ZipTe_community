package org.gdg.zipte.api.service.board.like;

import org.gdg.zipte.api.controller.board.like.request.BoardReactionRequest;
import org.gdg.zipte.api.service.board.like.response.BoardReactionResponse;

public interface BoardReactionService {

    // 추가하기
    BoardReactionResponse create(BoardReactionRequest request);

    // 취소하기
    BoardReactionResponse delete(BoardReactionRequest request);
}
