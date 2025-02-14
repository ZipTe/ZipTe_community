package com.zipte.platform.adapter.out.jpa.board;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zipte.platform.domain.board.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryJpaEntity parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<CategoryJpaEntity> children;

    // from
    public static CategoryJpaEntity from(Category category) {
        CategoryJpaEntity entity = make(category); // 부모-자식 없이 생성

        entity.parent = from(category.getParent()); // 부모 설정
        entity.children = category.getChildren().stream()
                .map(CategoryJpaEntity::from) // 자식 리스트 설정
                .collect(Collectors.toList());

        return entity;
    }

    //
    public static CategoryJpaEntity make(Category category) {
        return CategoryJpaEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .code(category.getCode())
                .build();
    }


    // toDomain
    public static Category toDomain(CategoryJpaEntity entity){
        if (entity == null) return null;

        Category category = make(entity); // 부모-자식 없이 생성

        category.setParent(toDomain(entity.getParent())); // 부모 설정
        category.setChildren(entity.getChildren().stream()
                .map(CategoryJpaEntity::toDomain) // 자식 리스트 설정
                .collect(Collectors.toList()));

        return category;
    }

    public static Category make(CategoryJpaEntity entity){
        return Category.of(entity.getId(), entity.getName(), entity.getCode());
    }
}
