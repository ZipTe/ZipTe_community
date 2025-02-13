package com.zipte.platform.adapter.out.jpa.board;


import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.user.Member;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private BoardSnippetJpaEntity boardSnippet;

    @Embedded
    private BoardStatisticsJpaEntity boardStatistics;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardCategoryJpaEntity> boardCategories = new ArrayList<>();

    // From
    public static BoardJpaEntity from(Board board) {
        return BoardJpaEntity.builder()
                .id(board.getId())
                .member(board.getMember())
                .boardSnippet(BoardSnippetJpaEntity.from(board.getSnippet()))
                .build();
    }

    // toDomain
    public Board toDomain() {
        return Board.of(this.id, this.member, this.boardSnippet.toDomain(), this.boardStatistics.toDomain());
    }

}
