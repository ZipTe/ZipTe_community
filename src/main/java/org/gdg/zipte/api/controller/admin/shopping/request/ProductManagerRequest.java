package org.gdg.zipte.api.controller.admin.shopping.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductManagerRequest {
    private Long productId;
    private int discountRate;
    private String couponCode;
    private boolean active;
    private Date saleStartDate;
    private Date saleEndDate;
    private String description;
    private int maxSalePrice;


}
