package org.gdg.zipte_gdg.api.service.member;

import org.gdg.zipte_gdg.api.controller.member.request.MemberRequestDto;
import org.gdg.zipte_gdg.api.service.member.response.MemberResponseDto;
import org.gdg.zipte_gdg.domain.member.Address;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.role.Role;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface MemberService {

    // 등록
    MemberResponseDto register (MemberRequestDto memberRequestDto);

    // 조회
    MemberResponseDto getOne(Long id);

    // 삭제

    // 소셜 로그인이후,주소 추가
    MemberResponseDto addAddress(MemberRequestDto memberRequestDto);

    default Member dtoToEntity(MemberRequestDto memberRequestDto) {
        Address address = Address.newAddress(memberRequestDto.getCity(), memberRequestDto.getStreetAddress(), memberRequestDto.getZipCode());
        return Member.createNewMember(memberRequestDto.getEmail(), memberRequestDto.getUsername(), memberRequestDto.getPassword(), memberRequestDto.getPhoneNumber(), address);
    }

    default MemberResponseDto entityToDto(Member member) {
        // roles가 null일 수 있으므로 빈 리스트로 초기화
        List<String> roleList = Optional.ofNullable(member.getRoles())
                .orElse(Collections.emptyList())
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toList());

        // address가 null일 수 있으므로 기본값 처리
        Address address = member.getAddress();
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .phoneNumber(member.getPhoneNumber())
                .city(address != null ? address.getCity() : "")
                .streetAddress(address != null ? address.getStreetAddress() : "")
                .roles(roleList)
                .zipCode(address != null ? address.getZipcode() : 00000)
                .build();
    }


}
