package org.gdg.zipte_gdg.api.controller.shopping.category;


import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.category.request.CategoryRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.category.CategoryService;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ApiResponse<List<CategoryResponse>> findAll() {
        return ApiResponse.created(categoryService.findAll());
    }

    @PostMapping
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(categoryService.save(categoryRequest));
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.created(categoryService.getCategory(id));
    }
}
