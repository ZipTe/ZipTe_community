package org.gdg.zipte.api.controller.admin.board;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.admin.product.request.CategoryRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.service.board.category.BoardCategoryService;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class AdminBoardController {

    private final BoardCategoryService boardCategoryService;

    // 카테고리 추가하기
    @PostMapping("/category")
    public ApiResponse<BoardCategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(boardCategoryService.save(categoryRequest));
    }

}
