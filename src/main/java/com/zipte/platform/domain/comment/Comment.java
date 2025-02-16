package com.zipte.platform.domain.comment;

import com.zipte.platform.domain.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.user.Member;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment {

    private Long id;
    private Board board;
    private Member member;
    private String content;
    private Comment parent;
    private List<Comment> children;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public static Comment of(Board board, Member member, Comment parent, String content) {

        return Comment.builder()
                .board(board)
                .member(member)
                .parent(parent)
                .content(content)
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
