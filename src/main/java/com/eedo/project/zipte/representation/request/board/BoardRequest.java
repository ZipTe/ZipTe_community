package com.eedo.project.zipte.representation.request.board;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class BoardRequest {

    // 카테고리
    private Long categoryId;

    // 게시물
    private Long memberId;
    private String title;
    private String content;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();



}
