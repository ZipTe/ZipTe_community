package com.zipte.platform.domain.apt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AptSnippet {

    // 버스 시간
    private int withTimeBus;
    // 지하철 시간
    private int withTimeSubway;
    // 세대수
    private int houseHolds;

    // 생성자
    public AptSnippet of (int withTimeBus, int withTimeSubway, int houseHolds) {
        return AptSnippet.builder()
                .withTimeBus(withTimeBus)
                .withTimeSubway(withTimeSubway)
                .houseHolds(houseHolds)
                .build();

    }
}
