package org.gdg.zipte.api.controller.admin.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {

    private String name;

    private String code;

    private Long parentId;

}
