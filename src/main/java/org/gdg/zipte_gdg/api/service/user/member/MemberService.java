package org.gdg.zipte_gdg.api.service.user.member;

import org.gdg.zipte_gdg.api.controller.user.request.MemberRequest;
import org.gdg.zipte_gdg.api.service.user.member.response.MemberResponse;

public interface MemberService {

    // 조회
    MemberResponse getOne(Long id);

    // 삭제

    // 소셜 로그인이후,주소 추가
    MemberResponse addAddress(MemberRequest memberRequest);

}
