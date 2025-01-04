package org.gdg.zipte.api.service.product.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.product.category.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryNoChildrenResponse {

    private Long id;
    private String name;

    // 생성자
    public static CategoryNoChildrenResponse from(Category category) {

        return CategoryNoChildrenResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
