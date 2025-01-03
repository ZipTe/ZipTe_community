package org.gdg.zipte.domain.product.productManger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.product.product.Product;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductManager {

    /*
        프로덕트 매니저는 상품의 관리를 하는 엔티티
        쿠폰 적용, 할인율, 판매 중지 등의 관리를 한다.
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    // 할인율
    private int discountRate;

    // 판매 여부
    private boolean active;

    // 판매 시작일
    private Date saleStartDate;

    // 판매 종료일
    private Date saleEndDate;

    // 쿠폰 코드
    private String couponCode;

    // 최대 할인 금액
    private int maxSalePrice;

    // 상품관리 설명
    private String description;


    // 프로덕트와 함께 만들어지기에 고치는 것으로 불러서 고친다
    // ProductManager 클래스
    public static ProductManager of(Product product, int discountRate, boolean active, String description) {
        return ProductManager.builder()
                .product(product)
                .discountRate(discountRate)
                .active(active)
                .description(description)
                .build();
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSaleStartDate(Date saleStartDate) {
        this.saleStartDate = saleStartDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

}
