package com.zipte.platform.adapter.out.jpa.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zipte.platform.adapter.out.jpa.board.BoardJpaEntity;
import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.comment.Comment;
import com.zipte.platform.domain.user.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class CommentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private BoardJpaEntity board;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    // 대댓글을 위한 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommentJpaEntity parent;

    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    @Builder.Default
    private List<CommentJpaEntity> children = new ArrayList<>();

    // 생성된 날짜
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    // from
    public static CommentJpaEntity from(Comment comment) {
        CommentJpaEntity entity = make(comment); // 부모-자식 없이 생성

        entity.parent = from(comment.getParent()); // 부모 설정
        entity.children = comment.getChildren().stream()
                .map(CommentJpaEntity::from) // 자식 리스트 설정
                .collect(Collectors.toList());

        return entity;
    }

    //
    public static CommentJpaEntity make(Comment comment) {
        return CommentJpaEntity.builder()
                .id(comment.getId())
                .board(BoardJpaEntity.from(comment.getBoard()))
                .member(comment.getMember())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }


    // toDomain
    public static Comment toDomain(CommentJpaEntity entity){
        if (entity == null) return null;

        Comment comment = make(entity); // 부모-자식 없이 생성

        comment.setParent(toDomain(entity.getParent())); // 부모 설정

        comment.setChildren(entity.getChildren().stream()
                .map(CommentJpaEntity::toDomain) // 자식 리스트 설정
                .collect(Collectors.toList()));

        return comment;
    }

    public static Comment make(CommentJpaEntity entity){

        Board board = entity.getBoard().toDomain();

        return Comment.builder()
                .id(entity.getId())
                .board(board)
                .member(entity.getMember())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .build();
    }

}
