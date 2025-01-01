package org.gdg.zipte_gdg.api.controller.user.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.user.member.request.MemberRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.user.member.MemberService;
import org.gdg.zipte_gdg.api.service.user.member.response.MemberResponse;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @PutMapping
    public ApiResponse<MemberResponse> update(@RequestBody MemberRequest memberRequest, @AuthenticationPrincipal PrincipalDetails principalDetails){

        memberRequest.setId(principalDetails.getId());
        return ApiResponse.created(memberService.addAddress(memberRequest));
    }

    @GetMapping("/myPage")
    public ApiResponse<MemberResponse> getOne(@AuthenticationPrincipal PrincipalDetails principalDetails){
        Long memberId = principalDetails.getId();
        return ApiResponse.created(memberService.getOne(memberId));
    }

}
