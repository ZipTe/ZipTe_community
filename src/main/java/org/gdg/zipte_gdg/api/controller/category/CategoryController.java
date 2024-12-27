package org.gdg.zipte_gdg.api.controller.category;


import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.category.request.CategoryRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.category.CategoryService;
import org.gdg.zipte_gdg.api.service.category.response.CategoryResponse;
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
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequestDto categoryRequestDto) {
        return ApiResponse.created(categoryService.save(categoryRequestDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.created(categoryService.getCategory(id));
    }
}
