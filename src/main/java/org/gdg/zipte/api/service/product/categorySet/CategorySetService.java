package org.gdg.zipte.api.service.product.categorySet;

import org.gdg.zipte.api.controller.admin.product.request.ProductRequest;
import org.gdg.zipte.api.service.product.productManger.response.DiscountProductResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;
import org.gdg.zipte.api.service.product.categorySet.response.CategorySetResponse;

public interface CategorySetService {

    // 특정 카테고리에 아이템 생성하기
    CategorySetResponse create(ProductRequest categorySetRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponse<ProductResponse> findAllAdmin(Long id, PageRequest pageRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponse<DiscountProductResponse> findAll(Long id, PageRequest pageRequest);

}
