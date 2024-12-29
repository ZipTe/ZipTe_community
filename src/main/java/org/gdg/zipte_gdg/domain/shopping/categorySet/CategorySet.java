package org.gdg.zipte_gdg.domain.shopping.categorySet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.category.Category;
import org.gdg.zipte_gdg.domain.shopping.product.Product;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class CategorySet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public static CategorySet of(Product product, Category category) {
        return CategorySet.builder()
                .product(product)
                .category(category)
                .build();
    }

}
