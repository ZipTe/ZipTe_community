package org.gdg.zipte_gdg.security;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.service.member.response.MemberResponseDto;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Log4j2
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;
    // username은 id에 해당하는 값
    // 이번 경우는 email이 해당
    // userDetail이 dto에 해당
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("---------loadUserByUsername--------"+username);
        Optional<Member> result = memberRepository.findByEmail(username);
        Member member = result.orElseThrow();

//        MemberResponseDto memberDTO = new MemberResponseDto(member.getEmail(),
//                member.getPw(),
//                member.getNickName(),
//                member.isSocial(),
//                member.getMemberRoleList().stream().map(memberRole -> memberRole.name()).collect(Collectors.toList()));

        MemberResponseDto memberDTO = MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .build();

        return memberDTO;
    }
}
