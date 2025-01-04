package org.gdg.zipte.api.service.apt.apt.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AptResposnseDto {

    private String kaptCode;
    private String kaptName;

}
