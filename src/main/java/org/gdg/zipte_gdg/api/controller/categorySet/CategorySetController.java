package org.gdg.zipte_gdg.api.controller.categorySet;


import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.categorySet.CategorySetService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/category/product")
public class CategorySetController {

    private final CategorySetService categorySetService;

    @GetMapping("/{id}")
    public ApiResponse<PageResponseDto<ProductResponseDto>> getProductCategory(@PathVariable("id") Long id, PageRequestDto pageRequestDto) {
        return ApiResponse.created(categorySetService.findAllById(id,pageRequestDto));
    }
}
