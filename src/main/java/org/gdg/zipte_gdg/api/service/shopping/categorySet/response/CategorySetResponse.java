package org.gdg.zipte_gdg.api.service.shopping.categorySet.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.domain.product.category.Category;
import org.gdg.zipte_gdg.domain.product.categorySet.CategorySet;
import org.gdg.zipte_gdg.domain.product.product.Product;


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
