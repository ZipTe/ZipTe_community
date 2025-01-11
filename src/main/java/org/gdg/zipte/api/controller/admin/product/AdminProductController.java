package org.gdg.zipte.api.controller.admin.product;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.admin.product.request.CategoryRequest;
import org.gdg.zipte.api.controller.admin.product.request.ProductRequest;
import org.gdg.zipte.api.controller.admin.product.request.ProductManagerRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.service.product.category.CategoryService;
import org.gdg.zipte.api.service.product.category.response.CategoryResponse;
import org.gdg.zipte.api.service.product.categorySet.CategorySetService;
import org.gdg.zipte.api.service.product.categorySet.response.CategorySetResponse;
import org.gdg.zipte.api.service.product.product.ProductService;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;
import org.gdg.zipte.api.service.product.productManger.ProductMangerService;
import org.gdg.zipte.api.service.product.productManger.response.ProductManagerResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
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
    @PostMapping
    public ApiResponse<CategorySetResponse> create(ProductRequest productRequest) {
        return ApiResponse.created(categorySetService.create(productRequest));
    }

    // 상품 자체 목록 조회
    @GetMapping("/list")
    public ApiResponse<PageResponse<ProductResponse>> getList(PageRequest pageRequest) {
        return ApiResponse.created(productService.findAll(pageRequest));
    }

    // 상품 상세 조회
    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> get(@PathVariable Long productId) {
        return ApiResponse.created(productService.findById(productId));
    }

    // 카테고리 추가하기
    @PostMapping("/category")
    public ApiResponse<CategoryResponse> save(@RequestBody CategoryRequest categoryRequest) {
        return ApiResponse.created(categoryService.save(categoryRequest));
    }

    // 카테고리에 맞는 상품 자체 조회
    @GetMapping("/category/{id}")
    public ApiResponse<PageResponse<ProductResponse>> getProductCategory(@PathVariable("id") Long id, PageRequest pageRequest) {
        return ApiResponse.created(categorySetService.findAllAdmin(id, pageRequest));
    }

    // 상품 매니저 추가
    @PostMapping("/manager")
    public ApiResponse<ProductManagerResponse> create(@RequestBody ProductManagerRequest productManagerRequest) {
        return ApiResponse.created(productMangerService.create(productManagerRequest));
    }

}
