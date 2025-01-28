package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import com.eedo.project.zipte.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
public class ProductResponse {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;
    
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();


    public static ProductResponse from(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .pname(product.getPname())
                .stock(product.getStock())
                .price(product.getPrice())
                .pdesc(product.getPdesc())
                .build();
    }
}
