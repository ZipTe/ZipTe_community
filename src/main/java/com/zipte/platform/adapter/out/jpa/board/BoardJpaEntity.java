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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CategoryJpaEntity> boardCategories = new ArrayList<>();

    // From
    public static BoardJpaEntity from(Board board) {
        return BoardJpaEntity.builder()
                .memberId(board.getId())
                .boardSnippet(BoardSnippetJpaEntity.from(board.getSnippet()))
                .boardCategories(board.getCategories()
                        .stream().map(CategoryJpaEntity::from).toList())
                .build();
    }

    // toDomain
    public Board toDomain() {
        return Board.builder()
                .id(this.getId())
                .snippet(this.getBoardSnippet().toDomain())
                .statistics(this.getBoardStatistics().toDomain())
                .memberId(this.getMemberId())
                .categories(this.getBoardCategories().stream().map(CategoryJpaEntity::toDomain).toList())
                .build();
    }
    

}
