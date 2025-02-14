package com.zipte.platform.application.port.out;

import com.zipte.platform.domain.board.Category;

import java.util.List;
import java.util.Optional;

public interface LoadCategoryPort {

    List<Category> findAllById(List<Long> categoryIds);

    Optional<Category> loadCategory(Long categoryId);

}
