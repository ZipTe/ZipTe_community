package com.zipte.platform.domain.apt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Apt {

    private String id;
    private String aptName;

    private AptCategory category;
    private AptAddress aptAddress;
    private AptSnippet snippet;

    // 생성자
    public Apt of(String aptName, AptCategory category, AptAddress aptAddress, AptSnippet snippet) {
        return Apt.builder()
                .aptName(aptName)
                .category(category)
                .aptAddress(aptAddress)
                .snippet(snippet)
                .build();
    }


}
