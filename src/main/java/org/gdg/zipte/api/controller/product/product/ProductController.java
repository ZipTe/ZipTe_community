package org.gdg.zipte.api.controller.product.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte.api.service.product.category.CategoryService;
import org.gdg.zipte.api.service.product.category.response.CategoryResponse;
import org.gdg.zipte.api.service.product.productManger.ProductMangerService;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.product.categorySet.CategorySetService;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.api.service.product.product.ProductImageService;
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
    public ApiResponse<PageResponseDto<DiscountProductResponse>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(productMangerService.findAll(pageRequestDto));
    }

    // 상품 상세 조회
    @GetMapping("/{id}")
    public ApiResponse<DiscountProductResponse> findById(@PathVariable Long id) {
        return ApiResponse.created(productMangerService.findById(id));
    }

    // 사진 정보 보기
    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        return productImageService.getFile(fileName);
    }

    // 카테고리 모두 보기
    @GetMapping("/category")
    public ApiResponse<List<CategoryResponse>> findAll() {
        return ApiResponse.created(categoryService.findAll());
    }

    // 카테고리 상세 조회
    @GetMapping("/category/{id}")
    public ApiResponse<CategoryResponse> getCategory(@PathVariable Long id) {
        return ApiResponse.created(categoryService.getCategory(id));
    }

    // 카테고리별 아이템 조회
    @GetMapping("/category/list/{id}")
    public ApiResponse<PageResponseDto<DiscountProductResponse>> getDiscountProductAll(@PathVariable("id") Long id, PageRequestDto pageRequestDto) {
        return ApiResponse.created(categorySetService.findAll(id,pageRequestDto));
    }
}
