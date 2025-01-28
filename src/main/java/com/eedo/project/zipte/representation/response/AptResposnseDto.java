package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AptResposnseDto {

    private String kaptCode;
    private String kaptName;

}
