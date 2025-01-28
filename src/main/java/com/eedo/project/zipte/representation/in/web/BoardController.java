package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.board.BoardRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.BoardService;
import com.eedo.project.zipte.representation.response.BoardResponse;
import com.eedo.project.zipte.application.port.in.BoardCategoryService;
import com.eedo.project.zipte.representation.response.BoardCategoryResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
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
