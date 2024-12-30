package org.gdg.zipte_gdg.domain.shopping.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    private String pname;

    private String pdesc;

    private int price;

    private int stock;

    private String manufacturer;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductManager> managers = new ArrayList<>();

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @Builder.Default
    private List<ProductImage> productImages = new ArrayList<>();

    private LocalDateTime createdAt;

    //생성
    public static Product of(String pname, String pdesc, int price, int stock) {
        Product product = Product.builder()
                .pname(pname)
                .pdesc(pdesc)
                .stock(stock)
                .price(price)
                .createdAt(LocalDateTime.now())
                .build();

        // 기본 ProductManager 추가
        ProductManager defaultManager = ProductManager.builder()
                .product(product)
                .discountRate(0)
                .active(false)
                .build();
        product.managers.add(defaultManager);

        return product;
    }

    // 로직
    public void addStock(int count) {
        this.stock += count;
    }

    public void removeStock(int count) {
        this.stock -= count;
    }

    // 이미지 로직
    public void addProductImage(ProductImage productImage) {
        productImage.setProduct(this);
        this.productImages.add(productImage);
        productImage.setOrd(this.productImages.size()-1);

    }

    public void removeProductImage(ProductImage productImage) {
        this.productImages.remove(productImage);
    }

}
