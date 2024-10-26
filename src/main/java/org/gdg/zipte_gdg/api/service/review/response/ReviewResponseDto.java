package org.gdg.zipte_gdg.api.service.review.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private Date createdAt;
    private Date updatedAt;

}
