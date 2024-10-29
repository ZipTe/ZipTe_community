package org.gdg.zipte_gdg.api.service.review.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.api.service.comment.response.CommentResponseWithReviewDto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ReviewResponseWithCommentDto {

    private Long id;
    private String title;
    private Long memberId;
    private String author;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private List<CommentResponseWithReviewDto> comments;

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

}
