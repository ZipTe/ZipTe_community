package org.gdg.zipte.api.service.product.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.product.category.Category;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String name;

    @Builder.Default
    private List<CategoryResponse> children = new ArrayList<>();

    // 자식 카테고리를 재귀적으로 처리하여 자식의 자식도 추가
    private void setChildren(List<Category> children) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            children.forEach(category -> {
                CategoryResponse child = CategoryResponse.from(category);
                this.children.add(child);  // 자식 카테고리를 추가
            });
        }
    }


    // 생성자
    public static CategoryResponse from(Category category) {
        CategoryResponse categoryResponse = CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        categoryResponse.setChildren(category.getChildren());

        return categoryResponse;

    }

    // 여러개 일때 생성자
    public static List<CategoryResponse> froms(List<Category> categories) {
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : categories) {
            responseList.add(CategoryResponse.from(category));
        }
        return responseList;
    }
}
