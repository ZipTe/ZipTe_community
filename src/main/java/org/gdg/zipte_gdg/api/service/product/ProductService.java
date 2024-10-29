package org.gdg.zipte_gdg.api.service.product;


import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.controller.product.request.ProductRequestDto;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.domain.product.Product;

public interface ProductService {

    //등록
    ProductResponseDto register(ProductRequestDto productRequestDto);

    //찾기
    ProductResponseDto findById(Long id);

    //아이템 리스트 조회
    PageResponseDto<ProductResponseDto> findAll(PageRequestDto pageRequestDto);

    //아이템 수정
    //아이템 삭제


    //dto에서 전환
    default Product dtoToEntity(ProductRequestDto dto) {
        return Product.createNewProduct(dto.getPname(), dto.getPdesc(), dto.getPrice(), dto.getStock());
    }

    //entity에서 전환
    default ProductResponseDto entityToDto(Product entity) {
        return ProductResponseDto.builder()
                .id(entity.getId())
                .pname(entity.getPname())
                .pdesc(entity.getPdesc())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .build();
    }

}
