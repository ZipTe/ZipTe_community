package org.gdg.zipte_gdg.api.controller.shopping.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.gdg.zipte_gdg.api.controller.shopping.categorySet.request.CategorySetRequest;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.CategorySetService;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.product.ProductImageService;
import org.gdg.zipte_gdg.api.service.shopping.product.ProductService;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;
    private final CategorySetService categorySetService;
    private final ProductImageService productImageService;

    @PostMapping
    public ApiResponse<CategorySetResponse> create(CategorySetRequest categorySetRequest) {
        return ApiResponse.created(categorySetService.create(categorySetRequest));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponseDto<ProductResponse>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(productService.findAll(pageRequestDto));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponse> get(@PathVariable Long productId) {
        return ApiResponse.created(productService.findById(productId));
    }

    @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
        return productImageService.getFile(fileName);
    }
}
