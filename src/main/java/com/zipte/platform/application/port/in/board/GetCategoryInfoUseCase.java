package com.zipte.platform.application.port.in.board;

import com.zipte.platform.domain.board.Category;

import java.util.List;

public interface GetCategoryInfoUseCase {

    // 루트 카테고리들 조회
    List<Category> getRootInfo();

    // 카테고리 아이디로 조회
    Category getByCategoryId(Long categoryId);


}
