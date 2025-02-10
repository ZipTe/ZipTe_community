package com.zipte.platform.adapter.in.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.board.BoardCategory;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCategoryResponse {

    private Long id;
    private String name;

    @Builder.Default
    private List<BoardCategoryResponse> children = new ArrayList<>();

    // 자식 카테고리를 재귀적으로 처리하여 자식의 자식도 추가
    private void setChildren(List<BoardCategory> children) {
        if (children == null) {
            this.children = new ArrayList<>();
        } else {
            children.forEach(category -> {
                BoardCategoryResponse child = BoardCategoryResponse.from(category);
                this.children.add(child);  // 자식 카테고리를 추가
            });
        }
    }


    // 생성자
    public static BoardCategoryResponse from(BoardCategory category) {
        BoardCategoryResponse boardCategoryResponse = BoardCategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();

        boardCategoryResponse.setChildren(category.getChildren());

        return boardCategoryResponse;

    }

    // 여러개 일때 생성자
    public static List<BoardCategoryResponse> froms(List<BoardCategory> categories) {
        List<BoardCategoryResponse> responseList = new ArrayList<>();
        for (BoardCategory category : categories) {
            responseList.add(BoardCategoryResponse.from(category));
        }
        return responseList;
    }
}
