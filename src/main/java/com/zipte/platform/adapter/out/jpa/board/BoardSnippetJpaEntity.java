package com.zipte.platform.adapter.out.jpa.board;

import com.zipte.platform.domain.board.BoardSnippet;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardSnippetJpaEntity {

    private String title;
    private String content;
    private String description;
    private String thumbnailUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // from
    public static BoardSnippetJpaEntity from(BoardSnippet boardSnippet) {
        return BoardSnippetJpaEntity.builder()
                .title(boardSnippet.getTitle())
                .content(boardSnippet.getContent())
                .description(boardSnippet.getDescription())
                .thumbnailUrl(boardSnippet.getThumbnailUrl())
                .createdAt(boardSnippet.getCreatedAt())
                .updatedAt(boardSnippet.getUpdatedAt())
                .build();
    }

    // toDomain
    public BoardSnippet toDomain(){
        return BoardSnippet.of(this.getTitle(), this.getContent(), this.getDescription(), this.getThumbnailUrl(), this.getCreatedAt(), this.getUpdatedAt());
    }

}
