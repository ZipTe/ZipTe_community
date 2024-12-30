package org.gdg.zipte_gdg.domain.shopping.productManger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.domain.shopping.product.Product;

import java.time.LocalDateTime;

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
    private LocalDateTime saleStartDate;

    // 판매 종료일
    private LocalDateTime saleEndDate;

    // 쿠폰 코드
    private String couponCode;


}
