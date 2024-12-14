package org.gdg.zipte_gdg.api.service.apt.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AptResposnseDto {

    private String kaptCode;
    private String kaptName;

}
