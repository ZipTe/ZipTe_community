package com.zipte.platform.adapter.in.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.board.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String name;

    @Builder.Default
    private List<CategoryResponse> children = new ArrayList<>();

    // 단일 객체 변환
    public static CategoryResponse from(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .children(from(category.getChildren())) // 리스트 변환도 같은 메서드 사용
                .build();
    }

    // 리스트 변환
    public static List<CategoryResponse> from(List<Category> categories) {
        return categories == null ? List.of() : categories.stream()
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }
}
