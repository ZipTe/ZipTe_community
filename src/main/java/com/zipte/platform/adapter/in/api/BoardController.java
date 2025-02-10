package com.zipte.platform.adapter.in.api;

import lombok.RequiredArgsConstructor;
import com.zipte.platform.adapter.in.api.dto.request.board.BoardRequest;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.application.port.in.board.BoardService;
import com.zipte.platform.adapter.in.api.dto.response.BoardResponse;
import com.zipte.platform.application.port.in.board.BoardCategoryService;
import com.zipte.platform.adapter.in.api.dto.response.BoardCategoryResponse;
import com.zipte.core.common.page.request.PageRequest;
import com.zipte.core.common.page.response.PageResponse;
import com.zipte.core.security.oauth.domain.PrincipalDetails;
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
