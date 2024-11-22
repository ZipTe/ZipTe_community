package org.gdg.zipte_gdg.api.service.review.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String author;
    private String content;
    private String aptName;
    private int rating;
    private int viewCount;
    private Date createdAt;
    private Date updatedAt;

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

}
