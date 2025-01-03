package org.gdg.zipte.api.service.product.category;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.admin.shopping.request.CategoryRequest;
import org.gdg.zipte.api.service.product.category.response.CategoryResponse;
import org.gdg.zipte.domain.product.category.Category;
import org.gdg.zipte.domain.product.category.CategoryRepository;
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
                .orElseThrow();

        return CategoryResponse.from(category);
    }
}
