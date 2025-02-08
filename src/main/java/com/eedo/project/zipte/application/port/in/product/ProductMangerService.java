package com.eedo.project.zipte.application.port.in.product;

import com.eedo.project.zipte.representation.request.product.ProductManagerRequest;
import com.eedo.project.zipte.representation.response.DiscountProductResponse;
import com.eedo.project.zipte.representation.response.ProductManagerResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;

public interface ProductMangerService {

    // 매니저 생성하기
    ProductManagerResponse create(ProductManagerRequest request);

    // 할인된 금액으로 조회
    PageResponse<DiscountProductResponse> findAll(PageRequest pageRequest);

    // 단일 상품 조회
    DiscountProductResponse findById(Long id);

}
