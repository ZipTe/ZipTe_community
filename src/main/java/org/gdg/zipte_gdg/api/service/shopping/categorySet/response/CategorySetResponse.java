package org.gdg.zipte_gdg.api.service.shopping.categorySet.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryNoChildrenResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponse;
import org.gdg.zipte_gdg.domain.shopping.category.Category;
import org.gdg.zipte_gdg.domain.shopping.categorySet.CategorySet;
import org.gdg.zipte_gdg.domain.shopping.product.Product;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategorySetResponse {

    private CategoryNoChildrenResponse category;
    private ProductResponse product;

    // 생성자
    public static CategorySetResponse of(CategorySet categorySet) {

        Product product = categorySet.getProduct();
        ProductResponse productResponse = ProductResponse.of(product);

        Category category = categorySet.getCategory();
        CategoryNoChildrenResponse categoryResponse = CategoryNoChildrenResponse.of(category);

        return CategorySetResponse.builder()
                .category(categoryResponse)
                .product(productResponse)
                .build();
    }


}
