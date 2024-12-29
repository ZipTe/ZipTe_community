package org.gdg.zipte_gdg.api.service.categorySet;

import org.gdg.zipte_gdg.api.controller.categorySet.request.CategorySetRequestDto;
import org.gdg.zipte_gdg.api.controller.page.request.PageRequestDto;
import org.gdg.zipte_gdg.api.service.category.response.CategoryResponse;
import org.gdg.zipte_gdg.api.service.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.domain.eCommerce.category.Category;
import org.gdg.zipte_gdg.domain.eCommerce.product.Product;
import org.gdg.zipte_gdg.domain.eCommerce.categorySet.CategorySet;

public interface CategorySetService {

    // 특정 카테고리에 아이템 생성하기
    CategorySetResponse create(CategorySetRequestDto categorySetRequestDto);

    // 특정 카테고리에 있는 아이템 조회하기
    PageResponseDto<ProductResponseDto> findAllById(Long id, PageRequestDto pageRequestDto);


    /*
      DTO 변환
     */
    // 카테고리 및 아이템 관련

    default CategorySetResponse entityToDto(CategorySet categorySet) {
        Product product = categorySet.getProduct();

        Category category = categorySet.getCategory();
        CategoryResponse categoryResponse = new CategoryResponse(category);

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .pname(product.getPname())
                .stock(product.getStock())
                .price(product.getPrice())
                .pdesc(product.getPdesc())
                .build();

        return new CategorySetResponse(categoryResponse,productResponseDto);
    }

    // Product관련
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
