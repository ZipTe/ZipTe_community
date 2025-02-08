package com.eedo.project.zipte.representation.in.web.admin;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.product.CategoryRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.board.BoardCategoryService;
import com.eedo.project.zipte.representation.response.BoardCategoryResponse;
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
