package org.gdg.zipte_gdg.api.service.member.response.oauth2;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String role;
}