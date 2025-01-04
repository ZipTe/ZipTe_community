package org.gdg.zipte.api.service.board.boardReaction;

import org.gdg.zipte.api.controller.board.like.request.BoardReactionRequest;
import org.gdg.zipte.api.service.board.boardReaction.response.BoardReactionResponse;

public interface BoardReactionService {

    // 추가하기
    BoardReactionResponse create(BoardReactionRequest request);

    // 취소하기
    BoardReactionResponse delete(BoardReactionRequest request);
}
