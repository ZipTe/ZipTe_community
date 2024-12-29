package org.gdg.zipte_gdg.api.service.shopping.categorySet.response;

import lombok.Data;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;


@Data
public class CategorySetResponse {

    private CategoryResponse category;
    private ProductResponseDto product;


    public CategorySetResponse(CategoryResponse category, ProductResponseDto product) {
        this.category = category;
        this.product = product;
    }
}
