package org.gdg.zipte.api.service.product.product;


import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;

public interface ProductService {

    //찾기
    ProductResponse findById(Long id);

    //아이템 리스트 조회
    PageResponse<ProductResponse> findAll(PageRequest pageRequest);

    //아이템 수정

    //아이템 삭제

}
