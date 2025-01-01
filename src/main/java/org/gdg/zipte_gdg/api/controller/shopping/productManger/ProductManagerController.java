package org.gdg.zipte_gdg.api.controller.shopping.productManger;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.productManger.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.productManger.ProductMangerService;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product/manager")
public class ProductManagerController {

    private final ProductMangerService productMangerService;

    @PostMapping
    public ApiResponse<ProductManagerResponse> create(@RequestBody ProductManagerRequest productManagerRequest) {
        return ApiResponse.created(productMangerService.create(productManagerRequest));
    }

    @GetMapping("/list")
    public ApiResponse<PageResponseDto<DiscountProductResponse>> getList(PageRequestDto pageRequestDto) {
        return ApiResponse.created(productMangerService.findAll(pageRequestDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<DiscountProductResponse> findById(@PathVariable Long id) {
        return ApiResponse.created(productMangerService.findById(id));
    }
}
