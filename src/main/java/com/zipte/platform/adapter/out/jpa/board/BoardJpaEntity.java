package com.zipte.platform.adapter.out.jpa.board;


import com.zipte.platform.domain.board.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private Long memberId;

    @Embedded
    private BoardSnippetJpaEntity boardSnippet;

    @Embedded
    private BoardStatisticsJpaEntity boardStatistics;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryJpaEntity category;


    // From
    public static BoardJpaEntity from(Board board) {
        return BoardJpaEntity.builder()
                .memberId(board.getId())
                .boardSnippet(BoardSnippetJpaEntity.from(board.getSnippet()))
                .boardStatistics(BoardStatisticsJpaEntity.from(board.getStatistics()))
                .category(CategoryJpaEntity.from(board.getCategory()))
                .build();
    }

    // toDomain
    public Board toDomain() {
        return Board.builder()
                .id(this.getId())
                .snippet(this.getBoardSnippet().toDomain())
                .statistics(this.getBoardStatistics().toDomain())
                .memberId(this.getMemberId())
                .category(this.getCategory().toDomain())
                .build();
    }
    

}
