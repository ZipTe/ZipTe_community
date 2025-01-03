package org.gdg.zipte.api.service.product.categorySet;

import org.gdg.zipte.api.controller.admin.shopping.request.CategorySetRequest;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;
import org.gdg.zipte.api.service.product.categorySet.response.CategorySetResponse;

public interface CategorySetService {

    // 특정 카테고리에 아이템 생성하기
    CategorySetResponse create(CategorySetRequest categorySetRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponseDto<ProductResponse> findAllAdmin(Long id, PageRequestDto pageRequestDto);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponseDto<DiscountProductResponse> findAll(Long id, PageRequestDto pageRequestDto);

}
