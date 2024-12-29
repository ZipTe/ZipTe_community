package org.gdg.zipte_gdg.api.service.categorySet.response;

import lombok.Data;
import org.gdg.zipte_gdg.api.service.category.response.CategoryResponse;
import org.gdg.zipte_gdg.api.service.product.response.ProductResponseDto;


@Data
public class CategorySetResponse {

    private CategoryResponse category;
    private ProductResponseDto product;


    public CategorySetResponse(CategoryResponse category, ProductResponseDto product) {
        this.category = category;
        this.product = product;
    }
}
