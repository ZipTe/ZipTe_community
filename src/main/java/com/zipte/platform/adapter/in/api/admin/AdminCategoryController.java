package com.zipte.platform.adapter.in.api.admin;

import com.zipte.platform.application.port.in.board.CreateCategoryUseCase;
import com.zipte.platform.application.port.in.dto.request.board.CategoryRequest;
import lombok.RequiredArgsConstructor;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.adapter.in.api.dto.response.CategoryResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/board")
@RequiredArgsConstructor
public class AdminCategoryController {

    private final CreateCategoryUseCase createService;

    // 카테고리 추가하기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/category")
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(CategoryResponse.from(createService.createCategory(categoryRequest)));
    }

}
