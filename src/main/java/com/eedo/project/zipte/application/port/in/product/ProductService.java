package com.eedo.project.zipte.application.port.in.product;


import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.zipte.representation.response.ProductResponse;

public interface ProductService {

    //찾기
    ProductResponse findById(Long id);

    //아이템 리스트 조회
    PageResponse<ProductResponse> findAll(PageRequest pageRequest);

    //아이템 수정

    //아이템 삭제

}
