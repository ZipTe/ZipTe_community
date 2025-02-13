package com.zipte.platform.application.port.in.board;

import com.zipte.platform.adapter.in.api.dto.response.BoardCategoryResponse;

import java.util.List;

public interface GetCategoryInfoUseVase {

    // 전체 카테고리 보기
    List<BoardCategoryResponse> findAll();

    // 카테고리 아이디로 조회
    BoardCategoryResponse getCategory(Long categoryId);

}
