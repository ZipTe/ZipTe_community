package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.product.ProductRequest;
import com.eedo.project.zipte.representation.response.DiscountProductResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.ProductResponse;
import com.eedo.project.zipte.representation.response.CategorySetResponse;

public interface CategorySetService {

    // 특정 카테고리에 아이템 생성하기
    CategorySetResponse create(ProductRequest categorySetRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponse<ProductResponse> findAllAdmin(Long id, PageRequest pageRequest);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponse<DiscountProductResponse> findAll(Long id, PageRequest pageRequest);

}
