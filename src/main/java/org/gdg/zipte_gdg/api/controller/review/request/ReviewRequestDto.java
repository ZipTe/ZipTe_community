package org.gdg.zipte_gdg.api.controller.review.request;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ReviewRequestDto {

    private Long id;
    private String title;
    private String content;
    private Long memberId;
    private Long aptId;
    private int ratingScore;
    private LocalDateTime updatedAt;

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();

}
