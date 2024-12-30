package org.gdg.zipte_gdg.api.service.shopping.productManger.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.api.service.shopping.product.response.ProductResponseDto;

import java.util.Date;

@Data
@Builder
public class ProductManagerResponse {

    private ProductResponseDto product;
    private int discountRate;
    private String couponCode;
    private boolean active;
    private Date saleStartDate;
    private Date saleEndDate;

}
