package org.gdg.zipte_gdg.api.service.product.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;

}
