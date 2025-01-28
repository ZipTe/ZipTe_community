package com.eedo.project.zipte.representation.in.web.admin;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.product.CategoryRequest;
import com.eedo.project.zipte.representation.request.product.ProductRequest;
import com.eedo.project.zipte.representation.request.product.ProductManagerRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CategoryService;
import com.eedo.project.zipte.representation.response.CategoryResponse;
import com.eedo.project.zipte.application.port.in.CategorySetService;
import com.eedo.project.zipte.representation.response.CategorySetResponse;
import com.eedo.project.zipte.application.port.in.ProductService;
import com.eedo.project.zipte.representation.response.ProductResponse;
import com.eedo.project.zipte.application.port.in.ProductMangerService;
import com.eedo.project.zipte.representation.response.ProductManagerResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin/product")
public class AdminProductController {

    private final ProductMangerService productMangerService;
    private final CategorySetService categorySetService;
    private final ProductService productService;
    private final CategoryService categoryService;

    // 상품 추가
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ApiResponse<CategorySetResponse> create(ProductRequest productRequest) {
        return ApiResponse.created(categorySetService.create(productRequest));
    }

    // 상품 자체 목록 조회
    @GetMapping("/list")
    public ApiResponse<PageResponse<ProductResponse>> getList(PageRequest pageRequest) {
        return ApiResponse.ok(productService.findAll(pageRequest));
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> get(@PathVariable Long productId) {
        return ApiResponse.ok(productService.findById(productId));
    }

    // 카테고리 추가하기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/category")
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(categoryService.save(categoryRequest));
    }

    // 카테고리에 맞는 상품 자체 조회
    @GetMapping("/category/{id}")
    public ApiResponse<PageResponse<ProductResponse>> getProductCategory(@PathVariable("id") Long id, PageRequest pageRequest) {
        return ApiResponse.ok(categorySetService.findAllAdmin(id, pageRequest));
    }

    // 상품 매니저 추가
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/manager")
    public ApiResponse<ProductManagerResponse> create(@RequestBody ProductManagerRequest productManagerRequest) {
        return ApiResponse.created(productMangerService.create(productManagerRequest));
    }

}
