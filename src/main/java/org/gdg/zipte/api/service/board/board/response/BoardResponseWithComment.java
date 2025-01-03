package org.gdg.zipte.api.service.board.board.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.api.service.board.category.response.BoardCategoryNoChildrenResponse;
import org.gdg.zipte.api.service.board.comment.response.CommentResponse;
import org.gdg.zipte.domain.board.category.BoardCategory;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySet;
import org.gdg.zipte.domain.board.comment.Comment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseWithComment {

    private BoardCategoryNoChildrenResponse category;
    private BoardResponse board;
    private List<CommentResponse> comment;

    // 생성자
    public static BoardResponseWithComment from(BoardCategorySet categorySet) {

        // 카테고리
        BoardCategory category = categorySet.getCategory();
        BoardCategoryNoChildrenResponse categoryResponse = BoardCategoryNoChildrenResponse.from(category);


        // 게시판
        BoardResponse boardResponse = BoardResponse.from(categorySet);


        // 댓글
        List<Comment> comments = categorySet.getBoard().getComments();
        List<CommentResponse> commentResponses = CommentResponse.froms(comments);


        return BoardResponseWithComment.builder()
                .category(categoryResponse)
                .board(boardResponse)
                .comment(commentResponses)
                .build();
    }

}
