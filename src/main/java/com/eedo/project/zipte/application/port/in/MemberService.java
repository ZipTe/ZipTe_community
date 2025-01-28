package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.user.MemberRequest;
import com.eedo.project.zipte.representation.response.MemberResponse;

public interface MemberService {

    // 조회
    MemberResponse getOne(Long id);

    // 삭제

    // 소셜 로그인이후,주소 추가
    MemberResponse addAddress(MemberRequest memberRequest);

}
