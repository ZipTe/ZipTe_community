package com.zipte.platform.domain.board;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    private Long id;
    private Long memberId;
    private BoardSnippet snippet;
    private BoardStatistics statistics;
    private Category category;

    // 생성자
    public static Board of(Long memberId, BoardSnippet snippet, BoardStatistics statistics, Category category) {

        return Board.builder()
                .memberId(memberId)
                .snippet(snippet)
                .statistics(statistics)
                .category(category)
                .build();

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
}
