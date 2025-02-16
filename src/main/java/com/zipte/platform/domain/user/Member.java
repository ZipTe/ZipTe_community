package com.zipte.platform.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Member {

    private Long id;

    private String username;

    private String userThumbnail;

    // 로직
    public static Member of(Long id, String email, String username) {

        return Member.builder()
                .id(id)
                .username(username)
                .userThumbnail(email)
                .build();
    }
}