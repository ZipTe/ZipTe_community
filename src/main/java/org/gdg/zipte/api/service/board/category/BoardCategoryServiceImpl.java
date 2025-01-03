package org.gdg.zipte.api.service.board.category;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.admin.shopping.request.CategoryRequest;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryResponse;
import org.gdg.zipte.domain.board.category.BoardCategory;
import org.gdg.zipte.domain.board.category.BoardCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    public BoardCategoryResponse save(CategoryRequest categoryRequest) {

        // 부모가 없는 경우 null 처리
        BoardCategory parent = null;
        if (categoryRequest.getParentId() != null) {
            parent = boardCategoryRepository.findById(categoryRequest.getParentId()).orElseThrow();
        }

        BoardCategory category = BoardCategory.builder()
                .name(categoryRequest.getName())
                .code(categoryRequest.getCode())
                .parent(parent) // 부모가 null일 수도 있음
                .build();

        BoardCategory save = boardCategoryRepository.save(category);
        return BoardCategoryResponse.from(save);
    }


    @Override
    public List<BoardCategoryResponse> findAll() {
        List<BoardCategory> categories = boardCategoryRepository.findRootCategories();

        return BoardCategoryResponse.froms(categories);

    }

    @Override
    public BoardCategoryResponse getCategory(Long categoryId) {
        BoardCategory category = boardCategoryRepository.findById(categoryId)
                .orElseThrow();

        return BoardCategoryResponse.from(category);
    }
}
