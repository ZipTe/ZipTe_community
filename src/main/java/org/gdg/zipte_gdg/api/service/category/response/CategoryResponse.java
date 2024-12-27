package org.gdg.zipte_gdg.api.service.category.response;

import lombok.Data;
import org.gdg.zipte_gdg.domain.category.Category;

import java.util.ArrayList;
import java.util.List;

@Data
public class CategoryResponse {

    private Long id;
    private String name;
    List<CategoryResponse> children = new ArrayList<>();


    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        setChildren(category.getChildren());
    }

    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // 하위 계층 자식 카테고리 조회 및 객체 생성
    private void setChildren(List<Category> children) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {children.forEach(category -> {
            CategoryResponse child = new CategoryResponse(category.getId(), category.getName());
            this.children.add(child);
        });
        }
    }
}