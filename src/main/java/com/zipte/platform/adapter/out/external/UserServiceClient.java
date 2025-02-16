package com.zipte.platform.adapter.out.external;

import com.zipte.platform.application.port.out.LoadMemberPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;


@Slf4j
@Component
@RequiredArgsConstructor
public class UserServiceClient implements LoadMemberPort {

    private final RestTemplate restTemplate;
    private static final String USER_SERVICE_URL = "http://user-service/api/users/";

    @Override
    public boolean existsById(Long memberId) {
        try {
            restTemplate.headForHeaders(USER_SERVICE_URL + memberId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            return false;
        } catch (Exception e) {
            log.error("Failed to check user existence: {}", memberId, e);
            return false;
        }
    }
}
