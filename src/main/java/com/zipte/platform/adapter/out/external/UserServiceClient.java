package com.zipte.platform.adapter.out.external;

import com.zipte.platform.application.port.out.LoadMemberPort;
import com.zipte.platform.domain.user.Member;
import com.zipte.platform.adapter.out.external.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserServiceClient implements LoadMemberPort {

    private final RestTemplate restTemplate;

    @Override
    public Optional<Member> loadUser(Long memberId) {
        try {
            String url = "http://user-service/api/users/" + memberId;
            UserResponse response = restTemplate.getForObject(url, UserResponse.class);
            if (response != null) {
                return Optional.of(Member.of(response.getId(), response.getUsername(), response.getUserThumbnail()));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
