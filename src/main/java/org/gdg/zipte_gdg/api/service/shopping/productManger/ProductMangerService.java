package org.gdg.zipte_gdg.api.service.shopping.productManger;

import org.gdg.zipte_gdg.api.controller.admin.shopping.request.ProductManagerRequest;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.DiscountProductResponse;
import org.gdg.zipte_gdg.api.service.shopping.productManger.response.ProductManagerResponse;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;

public interface ProductMangerService {

    // 매니저 생성하기
    ProductManagerResponse create(ProductManagerRequest request);

    // 할인된 금액으로 조회
    PageResponseDto<DiscountProductResponse> findAll(PageRequestDto pageRequestDto);

    // 단일 상품 조회
    DiscountProductResponse findById(Long id);

}
