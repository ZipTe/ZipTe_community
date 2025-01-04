package org.gdg.zipte.api.service.product.productManger;

import org.gdg.zipte.api.controller.admin.product.request.ProductManagerRequest;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.api.service.product.productManger.response.ProductManagerResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;

public interface ProductMangerService {

    // 매니저 생성하기
    ProductManagerResponse create(ProductManagerRequest request);

    // 할인된 금액으로 조회
    PageResponse<DiscountProductResponse> findAll(PageRequest pageRequest);

    // 단일 상품 조회
    DiscountProductResponse findById(Long id);

}
