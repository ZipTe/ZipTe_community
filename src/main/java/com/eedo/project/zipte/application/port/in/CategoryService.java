package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.product.CategoryRequest;
import com.eedo.project.zipte.representation.response.CategoryResponse;

import java.util.*;

public interface CategoryService {

    // 새로운 카테고리를 추가
    CategoryResponse save(CategoryRequest categoryRequest);

    // 전체 카테고리 보기
    List<CategoryResponse> findAll();

    // 카테고리 아이디로 조회
    CategoryResponse getCategory(Long categoryId);

}
