package com.zipte.platform.application.port.in.user;

import com.zipte.platform.adapter.in.api.dto.request.user.MemberRequest;
import com.zipte.platform.adapter.in.api.dto.response.MemberResponse;

public interface MemberService {

    // 조회
    MemberResponse getOne(Long id);

    // 삭제

    // 소셜 로그인이후,주소 추가
    MemberResponse addAddress(MemberRequest memberRequest);

}
