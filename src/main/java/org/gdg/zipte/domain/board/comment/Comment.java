package org.gdg.zipte.domain.board.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.user.member.Member;

import java.time.LocalDateTime;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    // 대댓글을 위한 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Comment> children;

    // 생성된 날짜
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


    // 생성자
    public static Comment of(Board board, Member member, Comment parent, String content) {

        Comment comment = Comment.builder()
                .board(board)
                .member(member)
                .parent(parent)
                .content(content)
                .createdAt(LocalDateTime.now())
                .build();

        board.addComment(comment);

        return comment;
    }
}
