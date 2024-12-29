package org.gdg.zipte_gdg.api.service.shopping.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.category.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String name;
    private List<CategoryResponse> children = new ArrayList<>();

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        setChildren(category.getChildren());
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 자식 카테고리를 재귀적으로 처리하여 자식의 자식도 추가
    private void setChildren(List<Category> children) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            children.forEach(category -> {
                CategoryResponse child = new CategoryResponse(category);
                this.children.add(child);  // 자식 카테고리를 추가
            });
        }
    }
}
