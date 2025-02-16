package com.zipte.platform.domain.comment;

import com.zipte.platform.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment {

    private Long id;
    private Board board;
    private Long memberId;
    private String content;
    private Comment parent;
    private List<Comment> children;

    private CommentStatistics statistics;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public static Comment of(Board board, Long memberId, Comment parent, String content, CommentStatistics statistics) {

        return Comment.builder()
                .board(board)
                .memberId(memberId)
                .parent(parent)
                .content(content)
                .statistics(statistics)
                .createdAt(LocalDateTime.now())
                .build();

    }

    public void setParent(Comment parent) {
        this.parent = parent;
    }

    public void setChildren(List<Comment> children) {
        this.children = children;
    }
}
