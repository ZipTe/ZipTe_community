package com.eedo.project.zipte.representation.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.eedo.project.zipte.domain.board.BoardCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardCategoryNoChildrenResponse {

    private Long id;
    private String name;

    // 생성자
    public static BoardCategoryNoChildrenResponse from(BoardCategory category) {

        return BoardCategoryNoChildrenResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
