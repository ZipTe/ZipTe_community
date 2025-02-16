package com.zipte.platform.application.port.in.dto.request.board;

import lombok.Data;

@Data
public class CategoryRequest {

    private String name;

    private String code;

    private Long parentId;


}
