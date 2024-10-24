package org.gdg.zipte_gdg.api.service.review.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.gdg.zipte_gdg.api.service.comment.response.CommentResponseDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private List<CommentResponseDto> comments = new ArrayList<>();

}
