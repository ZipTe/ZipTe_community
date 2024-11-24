package org.gdg.zipte_gdg.security.oauth.service.response;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomUserDto {
    private Long id;
    private String email;
    private String username;
    private List<String> roles = new ArrayList<>();
}