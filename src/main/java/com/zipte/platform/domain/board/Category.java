package com.zipte.platform.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    private Long id;
    private String name;
    private String code;
    private Category parent;
    private List<Category> children;


    // 생성자
    public static Category of(Long id, String name, String code) {

        return Category.builder()
                .id(id)
                .name(name)
                .code(code)
                .build();
    }

    //
    public void setParent(Category parent) {
        this.parent = parent;
    }

    public void setChildren(List<Category> children) {
        this.children = children;
    }
}
