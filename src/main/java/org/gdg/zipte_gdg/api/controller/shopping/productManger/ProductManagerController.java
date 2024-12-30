package org.gdg.zipte_gdg.api.controller.shopping.productManger;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.productManger.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.ProductMangerService;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/product/manager")
public class ProductManagerController {

    private final ProductMangerService productMangerService;

    @PostMapping
    public ApiResponse<ProductManagerResponse> create(@RequestBody ProductManagerRequest productManagerRequest) {
        return ApiResponse.created(productMangerService.create(productManagerRequest));
    }
}
