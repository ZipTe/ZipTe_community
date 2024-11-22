package org.gdg.zipte_gdg.api.controller.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.member.request.MemberRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.member.MemberService;
import org.gdg.zipte_gdg.api.service.member.response.MemberResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ApiResponse<MemberResponseDto> register(@RequestBody MemberRequestDto memberRequestDto){
        return ApiResponse.created(memberService.register(memberRequestDto));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<MemberResponseDto> getOne(@PathVariable("memberId") Long memberId){
        return ApiResponse.created(memberService.getOne(memberId));
    }

}
