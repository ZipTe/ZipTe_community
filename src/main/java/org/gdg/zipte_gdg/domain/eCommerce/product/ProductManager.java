package org.gdg.zipte_gdg.domain.eCommerce.product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductManager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manager_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int discountRate; // 할인율

    private boolean isNotSold; // 판매여부

    private boolean hasCoupon; // 쿠폰 여부

    private String description; // 프로모션 설명

    // 할인 가격 계산
    public int calculateDiscountedPrice() {
        return product.getPrice() - (product.getPrice() * discountRate / 100);
    }

    // 쿠폰 적용 로직
    public int applyCoupon(int couponValue) {
        return Math.max(0, calculateDiscountedPrice() - couponValue);
    }

    // 상품 설정
    public void setProduct(Product product) {
        this.product = product;
    }

}

