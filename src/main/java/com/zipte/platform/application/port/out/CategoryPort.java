package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryPort {

    Category saveCategory(Category category);

    List<Category> loadAllByCategoryId(List<Long> categoryIds);

    Optional<Category> loadCategory(Long categoryId);

    List<Category> loadAllRootCategories();

    boolean getCheckedExistCategory(String code);

}
