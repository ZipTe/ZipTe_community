package org.gdg.zipte_gdg.api.service.member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.member.request.MemberRequestDto;
import org.gdg.zipte_gdg.api.service.member.response.MemberResponseDto;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponseDto register(MemberRequestDto memberRequestDto) {
        Member member = dtoToEntity(memberRequestDto);
        Member savedMember = memberRepository.save(member);

        return entityToDto(savedMember);
    }

    @Override
    public MemberResponseDto getOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return entityToDto(member);
    }
}
