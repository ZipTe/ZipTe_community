package org.gdg.zipte_gdg.api.controller.product.request;

import lombok.Data;

@Data
public class ProductRequestDto {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;

}
