package com.eedo.project.zipte.representation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.product.Category;

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
