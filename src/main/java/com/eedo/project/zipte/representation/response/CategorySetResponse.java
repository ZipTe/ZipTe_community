package com.eedo.project.zipte.representation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.product.Category;
import com.eedo.project.zipte.domain.product.CategorySet;
import com.eedo.project.zipte.domain.product.Product;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySetResponse {

    private CategoryNoChildrenResponse category;
    private ProductResponse product;

    // 생성자
    public static CategorySetResponse from(CategorySet categorySet) {

        Product product = categorySet.getProduct();
        ProductResponse productResponse = ProductResponse.from(product);

        Category category = categorySet.getCategory();
        CategoryNoChildrenResponse categoryResponse = CategoryNoChildrenResponse.from(category);

        return CategorySetResponse.builder()
                .category(categoryResponse)
                .product(productResponse)
                .build();
    }


}
