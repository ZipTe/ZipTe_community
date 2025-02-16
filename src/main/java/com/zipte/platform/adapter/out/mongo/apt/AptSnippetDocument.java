package com.zipte.platform.adapter.out.mongo.apt;

import com.zipte.platform.domain.apt.AptSnippet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AptSnippetDocument {

    private int withTimeBus;   // 버스 시간
    private int withTimeSubway; // 지하철 시간
    private int houseHolds;     // 세대수

    public static AptSnippetDocument from(AptSnippet snippet) {
        return AptSnippetDocument.builder()
                .withTimeBus(snippet.getWithTimeBus())
                .withTimeSubway(snippet.getWithTimeSubway())
                .houseHolds(snippet.getHouseHolds())
                .build();
    }

    public AptSnippet toDomain() {
        return AptSnippet.builder()
                .withTimeBus(this.getWithTimeBus())
                .withTimeSubway(this.getWithTimeSubway())
                .houseHolds(this.getHouseHolds())
                .build();
    }

}
