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
        if (category == null) {
            return null;
        }

        CategoryJpaEntity categoryJpaEntity = CategoryJpaEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .code(category.getCode())
                .parent(from(category.getParent()))  // 부모 변환
                .build();

        if (category.getChildren() != null) {
            List<CategoryJpaEntity> childEntities = category.getChildren().stream()
                    .map(child -> {
                        CategoryJpaEntity childEntity = from(child);
                        childEntity.parent = categoryJpaEntity;  // 부모 설정
                        return childEntity;
                    })
                    .collect(Collectors.toList());

            categoryJpaEntity.children = childEntities;
        }

        return categoryJpaEntity;
    }

    // toDomain
    public Category toDomain() {
        return new Category(
                this.id,
                this.name,
                this.code,
                this.parent != null ? this.parent.toDomain() : null,  // 부모 변환
                this.children != null
                        ? this.children.stream().map(CategoryJpaEntity::toDomain).collect(Collectors.toList())
                        : List.of()  // 자식 변환
        );
    }
}
