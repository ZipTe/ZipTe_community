package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.MemberService;
import com.eedo.project.zipte.representation.request.user.MemberRequest;
import com.eedo.project.zipte.representation.response.MemberResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
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
