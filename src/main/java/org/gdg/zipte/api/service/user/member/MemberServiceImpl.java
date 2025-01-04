package org.gdg.zipte.api.service.user.member;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.controller.user.member.request.MemberRequest;
import org.gdg.zipte.api.service.user.member.response.MemberResponse;
import org.gdg.zipte.domain.user.member.Address;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.MemberRepository;
import org.gdg.zipte.domain.user.member.Role;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberResponse getOne(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Member with ID " + id + " not found"));
        return MemberResponse.from(member);
    }

    @Override
    public MemberResponse addAddress(MemberRequest memberRequest) {
        // 회원 정보를 가져옵니다.
        Member member = memberRepository.findById(memberRequest.getId()).orElseThrow();

        // 회원의 역할이 ROLE_FIRST_JOIN_OAUTH_USER인지 확인합니다.
        if (member.getRoles().contains(Role.OAUTH_FIRST_JOIN)) {
            // 주소를 새로 생성합니다.
            Address address = Address.of(memberRequest.getDetailAddress(), memberRequest.getStreetAddress(), memberRequest.getZipCode());

            // 회원에 주소를 추가합니다.
            member.addAddress(address);
            member.removeMemberRole(Role.OAUTH_FIRST_JOIN);
            member.addMemberRole(Role.USER);
            // 회원 정보를 저장하고 DTO로 변환하여 반환합니다.
            memberRepository.save(member);
        } else {
            // ROLE_FIRST_JOIN_OAUTH_USER이 아닌 경우, 예외를 던지거나 다른 처리를 할 수 있습니다.
            // 예시로 예외를 던집니다:
            throw new IllegalStateException("주소 추가는 첫 번째 OAuth 회원만 가능합니다.");
        }

        return MemberResponse.from(member);
    }

}
