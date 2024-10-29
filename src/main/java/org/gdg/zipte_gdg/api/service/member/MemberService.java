package org.gdg.zipte_gdg.api.service.member;

import org.gdg.zipte_gdg.api.controller.member.request.MemberRequestDto;
import org.gdg.zipte_gdg.api.service.member.response.MemberResponseDto;
import org.gdg.zipte_gdg.domain.member.Address;
import org.gdg.zipte_gdg.domain.member.Member;

public interface MemberService {

    //등록
    MemberResponseDto register (MemberRequestDto memberRequestDto);

    //조회
    MemberResponseDto getOne(Long id);

    //삭제

    //수정

    default Member dtoToEntity(MemberRequestDto memberRequestDto) {
        Address address = Address.newAddress(memberRequestDto.getCity(), memberRequestDto.getStreetAddress(), memberRequestDto.getZipCode());
        return Member.createNewMember(memberRequestDto.getEmail(), memberRequestDto.getUsername(), memberRequestDto.getPassword(), memberRequestDto.getPhoneNumber(), address);
    }

    default MemberResponseDto entityToDto(Member member) {
        return MemberResponseDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .phoneNumber(member.getPhoneNumber())
                .city(member.getAddress().getCity())
                .streetAddress(member.getAddress().getStreetAddress())
                .zipCode(member.getAddress().getZipcode())
                .build();

    }

}
