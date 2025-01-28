package org.gdg.zipte.api.controller.user.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.controller.user.member.request.MemberRequest;
import org.gdg.zipte.api.common.ApiResponse;
import org.gdg.zipte.api.service.user.member.MemberService;
import org.gdg.zipte.api.service.user.member.response.MemberResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Log4j2
public class MemberController {

    private final MemberService memberService;

    // 개인정보 수정하기
    @PutMapping
    public ApiResponse<MemberResponse> update(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody MemberRequest memberRequest){

        memberRequest.setId(principalDetails.getId());
        return ApiResponse.created(memberService.addAddress(memberRequest));
    }

    // 마이페이지 조회하기
    @GetMapping("/myPage")
    public ApiResponse<MemberResponse> getOne(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Long memberId = principalDetails.getId();
        return ApiResponse.ok(memberService.getOne(memberId));
    }

    // 특정 유저 블라인드 처리 하기
}
