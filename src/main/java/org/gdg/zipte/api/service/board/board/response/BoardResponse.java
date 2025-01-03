package org.gdg.zipte.api.service.board.board.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryNoChildrenResponse;
import org.gdg.zipte.domain.board.board.Board;
import org.gdg.zipte.domain.board.category.BoardCategory;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySet;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponse {

    private BoardCategoryNoChildrenResponse category;
    private String title;
    private String content;

    private String author;

    private int viewCount;

    // 댓글 개수
    private int commentCount;

    private LocalDateTime createdAt;
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
                .title(board.getTitle())
                .content(board.getContent())
                .author(board.getMember().getUsername())
                .viewCount(board.getViewCount())
                .commentCount(board.getComments().size())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt() != null ? board.getUpdatedAt() : board.getCreatedAt())
                .build();
    }


}
