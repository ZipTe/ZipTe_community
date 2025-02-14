package com.zipte.platform.application.port.out;


import com.zipte.platform.domain.user.Member;

import java.util.Optional;

public interface LoadMemberPort {

    Optional<Member> loadUser(Long memberId);

}
