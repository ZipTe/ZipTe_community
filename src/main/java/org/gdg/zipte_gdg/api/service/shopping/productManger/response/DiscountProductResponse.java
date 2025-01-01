package org.gdg.zipte_gdg.api.service.shopping.productManger.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Getter
public class DiscountProductResponse {


    private Long id;
    private String category;
    private String pname;
    private String pdesc;
    private int stock;
    private int price;
    private int discountRate;
    private int discountPrice;

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

}
