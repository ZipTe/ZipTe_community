package com.eedo.project.zipte.application.port.in.board;

import com.eedo.project.zipte.adapter.in.api.dto.request.product.CategoryRequest;
import com.eedo.project.zipte.adapter.in.api.dto.response.BoardCategoryResponse;

import java.util.List;

public interface BoardCategoryService {

    // 새로운 카테고리를 추가
    BoardCategoryResponse save(CategoryRequest categoryRequest);

    // 전체 카테고리 보기
    List<BoardCategoryResponse> findAll();

    // 카테고리 아이디로 조회
    BoardCategoryResponse getCategory(Long categoryId);

}
