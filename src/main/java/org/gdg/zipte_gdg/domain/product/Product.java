package org.gdg.zipte_gdg.domain.product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;

    //생성
    public static Product createNewProduct(String pname, String pdesc, int price, int stock) {
        return Product.builder()
                .pname(pname)
                .pdesc(pdesc)
                .stock(stock)
                .price(price)
                .createdAt(LocalDateTime.now())
                .build();
    }

    // 로직
    public void addStock(int count) {
        this.stock += count;
    }

    public void removeStock(int count) {
        this.stock -= count;
    }

}
