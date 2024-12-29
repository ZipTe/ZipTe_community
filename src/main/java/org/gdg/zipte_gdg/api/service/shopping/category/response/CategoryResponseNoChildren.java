package org.gdg.zipte_gdg.api.service.shopping.category.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseNoChildren {

    private Long id;
    private String name;
    
}
