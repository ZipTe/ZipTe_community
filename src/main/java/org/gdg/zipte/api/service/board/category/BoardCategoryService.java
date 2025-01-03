package org.gdg.zipte.api.service.board.category;

import org.gdg.zipte.api.controller.admin.product.request.CategoryRequest;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryResponse;

import java.util.List;

public interface BoardCategoryService {

    // 새로운 카테고리를 추가
    BoardCategoryResponse save(CategoryRequest categoryRequest);

    // 전체 카테고리 보기
    List<BoardCategoryResponse> findAll();

    // 카테고리 아이디로 조회
    BoardCategoryResponse getCategory(Long categoryId);

}
