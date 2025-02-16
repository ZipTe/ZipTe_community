package com.zipte.platform.adapter.in.api;

import com.zipte.core.common.ApiResponse;
import com.zipte.platform.adapter.in.api.dto.response.CategoryResponse;
import com.zipte.platform.application.port.in.board.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {

    private final GetCategoryInfoUseCase getService;

    // 카테고리 상세 조회
    @GetMapping("/category/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {

        return ApiResponse.created(CategoryResponse.from(getService.getByCategoryId(id)));
    }
}
