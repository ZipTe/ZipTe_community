package org.gdg.zipte.api.service.product.categorySet.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.api.service.product.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte.api.service.product.product.response.ProductResponse;
import org.gdg.zipte.domain.product.category.Category;
import org.gdg.zipte.domain.product.categorySet.CategorySet;
import org.gdg.zipte.domain.product.product.Product;


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
