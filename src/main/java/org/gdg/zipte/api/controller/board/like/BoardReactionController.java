package org.gdg.zipte.api.controller.board.like;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.like.request.BoardReactionRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.like.BoardReactionService;
import org.gdg.zipte.api.service.board.like.response.BoardReactionResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reaction")
@RequiredArgsConstructor
public class BoardReactionController {

    private final BoardReactionService boardReactionService;

    @PostMapping
    public ApiResponse<BoardReactionResponse> create(@RequestBody  BoardReactionRequest request) {
        return ApiResponse.created(boardReactionService.create(request));
    }

    @DeleteMapping
    public ApiResponse<BoardReactionResponse> delete(@RequestBody BoardReactionRequest request) {
        return ApiResponse.created(boardReactionService.delete(request));
    }

}
