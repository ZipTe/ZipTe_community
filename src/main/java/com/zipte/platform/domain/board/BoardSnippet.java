package com.zipte.platform.domain.board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Builder
public class BoardSnippet {

    private String title;
    private String content;
    private String description;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 생성자
    public static BoardSnippet of(String title, String content, String description, String thumbnailUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return BoardSnippet.builder()
                .title(title)
                .content(content)
                .description(description)
                .thumbnailUrl(thumbnailUrl)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();

    }


}
