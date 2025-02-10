package com.eedo.project.zipte.adapter.in.api.admin;

import com.eedo.project.zipte.adapter.in.api.dto.request.board.CategoryRequest;
import lombok.RequiredArgsConstructor;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.board.BoardCategoryService;
import com.eedo.project.zipte.adapter.in.api.dto.response.BoardCategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class AdminBoardController {

    private final BoardCategoryService boardCategoryService;

    // 카테고리 추가하기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/category")
    public ApiResponse<BoardCategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(boardCategoryService.save(categoryRequest));
    }

}
