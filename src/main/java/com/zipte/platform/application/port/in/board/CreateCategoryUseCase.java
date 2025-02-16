package com.zipte.platform.application.port.in.board;

import com.zipte.platform.application.port.in.dto.request.board.CategoryRequest;
import com.zipte.platform.domain.board.Category;

public interface CreateCategoryUseCase {

    // 새로운 카테고리를 추가
    Category createCategory(CategoryRequest categoryRequest);


}
