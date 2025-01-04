package org.gdg.zipte.domain.board.categorySet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.category.BoardCategory;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardCategorySet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private BoardCategory category;


    public static BoardCategorySet of(Board board, BoardCategory category) {
        return BoardCategorySet.builder()
                .board(board)
                .category(category)
                .build();
    }

}
