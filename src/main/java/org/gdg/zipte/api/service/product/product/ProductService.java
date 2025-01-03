package org.gdg.zipte.api.service.product.product;


import org.gdg.zipte.domain.page.request.PageRequestDto;
import org.gdg.zipte.domain.page.response.PageResponseDto;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;

public interface ProductService {

    //찾기
    ProductResponse findById(Long id);

    //아이템 리스트 조회
    PageResponseDto<ProductResponse> findAll(PageRequestDto pageRequestDto);

    //아이템 수정

    //아이템 삭제

}
