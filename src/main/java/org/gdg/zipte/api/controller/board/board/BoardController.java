package org.gdg.zipte.api.controller.board.board;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.board.request.BoardRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.board.BoardService;
import org.gdg.zipte.api.service.board.board.response.BoardResponse;
import org.gdg.zipte.api.service.board.category.BoardCategoryService;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardCategoryService boardCategoryService;

    // 게시판 생성
    @PostMapping
    public ApiResponse<BoardResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, BoardRequest boardRequest) {
        boardRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(boardService.create(boardRequest));
    }

    @GetMapping("{boardId}")
    public ApiResponse<BoardResponse> getOne(@PathVariable Long boardId) {
        return ApiResponse.created(boardService.findOne(boardId));
    }

    // 카테고리 내 게시물 조회 (계층형 조회)
    @GetMapping("/category/list/{categoryId}")
    public ApiResponse<PageResponse<BoardResponse>> getByCategory(@PathVariable Long categoryId, PageRequest pageRequest) {
        return ApiResponse.created(boardService.findByCategoryId(categoryId, pageRequest));
    }

    // 카테고리 상세 조회
    @GetMapping("/category/{id}")
    public ApiResponse<BoardCategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.created(boardCategoryService.getCategory(id));
    }
}
