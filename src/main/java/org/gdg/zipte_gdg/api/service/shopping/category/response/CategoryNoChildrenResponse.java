package org.gdg.zipte_gdg.api.service.shopping.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.category.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryNoChildrenResponse {

    private Long id;
    private String name;

    // 생성자
    public static CategoryNoChildrenResponse of(Category category) {

        return CategoryNoChildrenResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
