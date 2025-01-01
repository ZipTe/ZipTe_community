package org.gdg.zipte_gdg.api.service.shopping.product.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
public class ProductResponseDto {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;
    
    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();
}
