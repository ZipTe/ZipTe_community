package org.gdg.zipte_gdg.api.service.shopping.categorySet;

import org.gdg.zipte_gdg.api.controller.shopping.categorySet.request.CategorySetRequest;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.response.CategorySetResponse;

public interface CategorySetService {

    // 특정 카테고리에 아이템 생성하기
    CategorySetResponse create(CategorySetRequest categorySetRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponseDto<ProductResponse> findAllAdmin(Long id, PageRequestDto pageRequestDto);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponseDto<DiscountProductResponse> findAll(Long id, PageRequestDto pageRequestDto);

}
