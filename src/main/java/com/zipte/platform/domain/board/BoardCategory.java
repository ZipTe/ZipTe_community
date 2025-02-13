package com.zipte.platform.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardCategory {

    private Long id;
    private Board board;
    private Category category;

    // 생성자
    public static BoardCategory of(Board board, Category category) {
        return BoardCategory.builder()
                .board(board)
                .category(category)
                .build();
    }

}
