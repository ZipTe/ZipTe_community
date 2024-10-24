package org.gdg.zipte_gdg.api.controller.product;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.product.request.ProductRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.ProductService;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponseDto> register(ProductRequestDto productRequestDto) {
        return ApiResponse.created(productService.register(productRequestDto));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponseDto<ProductResponseDto>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(productService.findAll(pageRequestDto));
    }

    @GetMapping("/{productId}")
    public ApiResponse<ProductResponseDto> get(@PathVariable Long productId) {
        return ApiResponse.created(productService.findById(productId));
    }
}
