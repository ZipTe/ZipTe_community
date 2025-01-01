package org.gdg.zipte_gdg.api.controller.shopping.product.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductRequest {

    private Long id;
    private String pname;
    private String pdesc;
    private int price;
    private int stock;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

}
