package org.gdg.zipte.api.controller.board.board;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.board.BoardService;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시판 생성
    @PostMapping
    public ApiResponse<BoardResponse> create (BoardRequest boardRequest) {
        return ApiResponse.created(boardService.create(boardRequest));
    }

    @GetMapping("{boardId}")
    public ApiResponse<BoardResponse> create (@PathVariable Long boardId) {
        return ApiResponse.created(boardService.findOne(boardId));
    }


}
