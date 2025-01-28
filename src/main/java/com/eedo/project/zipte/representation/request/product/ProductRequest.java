package com.eedo.project.zipte.representation.request.product;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ProductRequest {

    // 아이템 생성 관련
    private String pname;
    private String pdesc;
    private int price;
    private int stock;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

    // 이미 존재하는 카테고리와 연결
    private Long categoryId;



}
