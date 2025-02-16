package com.zipte.platform.domain.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;
    private Long memberId;
    private BoardSnippet snippet;
    private BoardStatistics statistics;
    private List<Category> categories = new ArrayList<>();

    // 생성자
    public static Board of(Long memberId, BoardSnippet snippet, BoardStatistics statistics, List<Category> categories) {

        Board board = Board.builder()
                .memberId(memberId)
                .snippet(snippet)
                .statistics(statistics)
                .build();

        board.setCategories(categories);
        return board;
    }

    // 양방향
    public void addComment() {
        this.getStatistics().addCommentCount();
    }

    public void addLikeReaction() {
        this.getStatistics().addLikeCount();
    }


    public void removeLikeReaction() {
        this.getStatistics().removeLikeCount();
    }


    public void setCategories(List<Category> categories) {
        this.categories = this.categories;
    }
}
