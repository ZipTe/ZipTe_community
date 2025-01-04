package org.gdg.zipte.api.service.board.board.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte.api.service.board.comment.response.CommentResponse;
import org.gdg.zipte.domain.board.categorySet.BoardCategorySet;
import org.gdg.zipte.domain.board.comment.Comment;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardResponseWithComment {

    private BoardResponse board;
    private List<CommentResponse> comment;

    // BoardResponseWithComment 클래스
    public static BoardResponseWithComment from(BoardCategorySet categorySet) {

        // 게시판 정보
        BoardResponse boardResponse = BoardResponse.from(categorySet);

        // 댓글 리스트
        List<Comment> comments = categorySet.getBoard().getComments();
        List<CommentResponse> commentResponses = CommentResponse.froms(comments);

        // 글 작성자 ID
        Long boardWriterId = categorySet.getBoard().getMember().getId();

        // 댓글 작성자와 글 작성자가 동일한지 확인
        for (CommentResponse commentResponse : commentResponses) {
            if (Objects.equals(commentResponse.getAuthor(), categorySet.getBoard().getMember().getUsername())) {
                commentResponse.setIsWriter(true);
            }
        }

        return BoardResponseWithComment.builder()
                .board(boardResponse)
                .comment(commentResponses)
                .build();
    }


}
