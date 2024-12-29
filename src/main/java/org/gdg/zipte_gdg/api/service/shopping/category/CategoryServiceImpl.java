package org.gdg.zipte_gdg.api.service.shopping.category;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.category.request.CategoryRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.category.response.CategoryResponse;
import org.gdg.zipte_gdg.domain.shopping.category.Category;
import org.gdg.zipte_gdg.domain.shopping.category.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse save(CategoryRequestDto categoryRequestDto) {
        // 부모가 없는 경우 null 처리
        Category parent = null;
        if (categoryRequestDto.getParentId() != null) {
            parent = categoryRepository.findById(categoryRequestDto.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent ID: " + categoryRequestDto.getParentId()));
        }

        Category category = Category.builder()
                .name(categoryRequestDto.getName())
                .code(categoryRequestDto.getCode())
                .parent(parent) // 부모가 null일 수도 있음
                .build();

        Category save = categoryRepository.save(category);
        return EntityToDto(save);
    }


    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findRootCategories();

        return EntityToDto(categories);

    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow();

        return EntityToDto(category);
    }
}
