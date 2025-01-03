package org.gdg.zipte.domain.board.board;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.gdg.zipte.domain.board.comment.Comment;
import org.gdg.zipte.domain.user.member.Member;
import org.hibernate.annotations.ColumnDefault;

import java.util.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ColumnDefault("0")
    @Column(name = "view_count",nullable = false)
    private int viewCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();


    // 생성자
    public static Board of(String title, String content, Member member) {

        return Board.builder()
                .title(title)
                .content(content)
                .member(member)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }


}
