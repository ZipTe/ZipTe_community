package com.eedo.project.zipte.application.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.product.CategoryRequest;
import com.eedo.project.core.common.exception.category.DuplicateCodeException;
import com.eedo.project.zipte.application.port.in.CategoryService;
import com.eedo.project.zipte.representation.response.CategoryResponse;
import com.eedo.project.zipte.domain.product.Category;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.product.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse save(CategoryRequest categoryRequest) {

        // 부모가 없는 경우 null 처리
        Category parent = null;
        if (categoryRequest.getParentId() != null) {
            parent = categoryRepository.findById(categoryRequest.getParentId())
                    .orElseThrow(() -> new NoSuchElementException("부모 카테고리가 존재하지 않습니다."));
        }

        // code 중복 체크
        if (categoryRequest.getCode() != null && categoryRepository.existsByCode(categoryRequest.getCode())) {
            throw new DuplicateCodeException("이미 존재하는 코드입니다.");
        }

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .code(categoryRequest.getCode())
                .parent(parent) // 부모가 null일 수도 있음
                .build();

        Category save = categoryRepository.save(category);
        return CategoryResponse.from(save);
    }


    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findRootCategories();

        return CategoryResponse.froms(categories);

    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new EntityNotFoundException("카테고리가 존재하지 않습니다"));

        return CategoryResponse.from(category);
    }
}
