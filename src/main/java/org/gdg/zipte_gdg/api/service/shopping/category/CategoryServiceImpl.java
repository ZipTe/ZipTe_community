package org.gdg.zipte_gdg.api.service.shopping.category;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.admin.shopping.request.CategoryRequest;
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
    public CategoryResponse save(CategoryRequest categoryRequest) {

        // 부모가 없는 경우 null 처리
        Category parent = null;
        if (categoryRequest.getParentId() != null) {
            parent = categoryRepository.findById(categoryRequest.getParentId()).orElseThrow();
        }

        Category category = Category.builder()
                .name(categoryRequest.getName())
                .code(categoryRequest.getCode())
                .parent(parent) // 부모가 null일 수도 있음
                .build();

        Category save = categoryRepository.save(category);
        return CategoryResponse.of(save);
    }


    @Override
    public List<CategoryResponse> findAll() {
        List<Category> categories = categoryRepository.findRootCategories();

        return CategoryResponse.ofs(categories);

    }

    @Override
    public CategoryResponse getCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow();

        return CategoryResponse.of(category);
    }
}
