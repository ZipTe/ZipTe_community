package org.gdg.zipte_gdg.api.service.category;

import org.gdg.zipte_gdg.api.controller.category.request.CategoryRequestDto;
import org.gdg.zipte_gdg.api.service.category.response.CategoryResponse;
import org.gdg.zipte_gdg.domain.category.Category;

import java.util.*;

public interface CategoryService {

    // 새로운 카테고리를 추가
    CategoryResponse save(CategoryRequestDto categoryRequestDto);

    // 전체 카테고리 보기
    List<CategoryResponse> findAll();

    // 카테고리 아이디로 조회
    CategoryResponse getCategory(Long categoryId);

    default CategoryResponse EntityToDto(Category category) {
        return new CategoryResponse(category);
    }

    default List<CategoryResponse> EntityToDto(List<Category> categories) {
        List<CategoryResponse> responseList = new ArrayList<>();
        for (Category category : categories) {
            responseList.add(EntityToDto(category));
        }
        return responseList;
    }



}
