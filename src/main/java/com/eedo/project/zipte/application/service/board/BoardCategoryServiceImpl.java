package com.eedo.project.zipte.application.service.board;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.adapter.in.api.dto.request.product.CategoryRequest;
import com.eedo.project.core.common.exception.category.DuplicateCodeException;
import com.eedo.project.zipte.application.port.in.board.BoardCategoryService;
import com.eedo.project.zipte.adapter.in.api.dto.response.BoardCategoryResponse;
import com.eedo.project.zipte.domain.board.BoardCategory;
import com.eedo.project.zipte.adapter.out.persistence.jpa.board.BoardCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardCategoryServiceImpl implements BoardCategoryService {

    private final BoardCategoryRepository boardCategoryRepository;

    @Override
    public BoardCategoryResponse save(CategoryRequest categoryRequest) {
        // 부모가 없는 경우 null 처리
        BoardCategory parent = null;
        if (categoryRequest.getParentId() != null) {
            parent = boardCategoryRepository.findById(categoryRequest.getParentId())
                    .orElseThrow(() -> new NoSuchElementException("부모 카테고리가 존재하지 않습니다."));
        }
        // code 중복 체크
        if (categoryRequest.getCode() != null && boardCategoryRepository.existsByCode(categoryRequest.getCode())) {
            throw new DuplicateCodeException("이미 존재하는 코드입니다: " + categoryRequest.getCode());
        }

        // 카테고리 생성
        BoardCategory category = BoardCategory.builder()
                .name(categoryRequest.getName())
                .code(categoryRequest.getCode())
                .parent(parent) // 부모가 null일 수도 있음
                .build();

        // 카테고리 저장
        BoardCategory savedCategory = boardCategoryRepository.save(category);

        // Response 변환
        return BoardCategoryResponse.from(savedCategory);
    }



    @Override
    public List<BoardCategoryResponse> findAll() {
        List<BoardCategory> categories = boardCategoryRepository.findRootCategories();

        return BoardCategoryResponse.froms(categories);

    }

    @Override
    public BoardCategoryResponse getCategory(Long categoryId) {
        BoardCategory category = boardCategoryRepository.findById(categoryId)
                .orElseThrow(()-> new EntityNotFoundException("카테고리가 존재하지 않습니다"));

        return BoardCategoryResponse.from(category);
    }
}
