package com.zipte.platform.adapter.in.api.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zipte.platform.domain.board.Board;
import com.zipte.platform.domain.board.BoardCategory;
import com.zipte.platform.domain.board.BoardCategorySet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {

    private BoardCategoryNoChildrenResponse category;

    private Long id;
    private String title;
    private String content;

    private String author;

    private int viewCount;

    // 댓글 개수
    private int commentCount;

    // 좋아요 개수
    private Long likeCount;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

    // 생성자
    public static BoardResponse from(BoardCategorySet categorySet) {

        Board board = categorySet.getBoard();

        BoardCategory category = categorySet.getCategory();
        BoardCategoryNoChildrenResponse categoryResponse = BoardCategoryNoChildrenResponse.from(category);

        return BoardResponse.builder()
                .category(categoryResponse)
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getMember().getUsername())
                .viewCount(board.getViewCount())
                .commentCount(board.getComments() != null ? board.getComments().size() : 0)
                .likeCount(board.getReactionScore() != null ? board.getReactionScore() : 0)
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }


}
