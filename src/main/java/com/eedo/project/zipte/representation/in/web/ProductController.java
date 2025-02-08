package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.eedo.project.zipte.application.port.in.CategoryService;
import com.eedo.project.zipte.representation.response.CategoryResponse;
import com.eedo.project.zipte.application.port.in.product.ProductMangerService;
import com.eedo.project.zipte.representation.response.DiscountProductResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CategorySetService;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.application.port.in.product.ProductImageService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductImageService productImageService;
    private final ProductMangerService productMangerService;
    private final CategoryService categoryService;
    private final CategorySetService categorySetService;

    // 상품 정보 전부 조회
    @GetMapping("/list")
    public ApiResponse<PageResponse<DiscountProductResponse>> getList(PageRequest pageRequest) {
        return ApiResponse.ok(productMangerService.findAll(pageRequest));
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    public ApiResponse<DiscountProductResponse> findById(@PathVariable Long id) {
        return ApiResponse.ok(productMangerService.findById(id));
    }

    // 사진 정보 보기
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        return productImageService.getFile(fileName);
    }

    // 카테고리 모두 보기
    @GetMapping("/category")
    public ApiResponse<List<CategoryResponse>> findAll() {
        return ApiResponse.ok(categoryService.findAll());
    }

    // 카테고리 상세 조회
    @GetMapping("/category/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.ok(categoryService.getCategory(id));
    }

    // 카테고리별 아이템 조회
    @GetMapping("/category/list/{id}")
    public ApiResponse<PageResponse<DiscountProductResponse>> getDiscountProductAll(@PathVariable("id") Long id, PageRequest pageRequest) {
        return ApiResponse.ok(categorySetService.findAll(id, pageRequest));
    }
}
