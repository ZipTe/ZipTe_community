package org.gdg.zipte_gdg.api.service.shopping.categorySet;

import org.gdg.zipte_gdg.api.controller.shopping.categorySet.request.CategorySetRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponseNoChildren;
import org.gdg.zipte_gdg.domain.page.request.PageRequestDto;
import org.gdg.zipte_gdg.domain.page.response.PageResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;
import org.gdg.zipte_gdg.api.service.shopping.categorySet.response.CategorySetResponse;
import org.gdg.zipte_gdg.domain.shopping.category.Category;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.categorySet.CategorySet;

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
        ProductResponseDto productResponseDto = getProductResponse(categorySet);

        CategoryResponseNoChildren categoryResponse = getCategoryResponse(categorySet);

        return new CategorySetResponse(categoryResponse,productResponseDto);
    }



    //

    private static ProductResponseDto getProductResponse(CategorySet categorySet) {
        Product product = categorySet.getProduct();

        ProductResponseDto productResponseDto = ProductResponseDto.builder()
                .id(product.getId())
                .pname(product.getPname())
                .stock(product.getStock())
                .price(product.getPrice())
                .pdesc(product.getPdesc())
                .build();
        return productResponseDto;
    }

    private static CategoryResponseNoChildren getCategoryResponse(CategorySet categorySet) {
        Category category = categorySet.getCategory();

        CategoryResponseNoChildren categoryResponse = CategoryResponseNoChildren.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
        return categoryResponse;
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
