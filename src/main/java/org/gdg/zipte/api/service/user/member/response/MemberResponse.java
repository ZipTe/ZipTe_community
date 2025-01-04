package org.gdg.zipte.api.service.user.member.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte.domain.user.member.Address;
import org.gdg.zipte.domain.user.member.Member;
import org.gdg.zipte.domain.user.member.Role;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
@Builder
public class MemberResponse {

    private Long id;
    private String email;
    private String username;
    private String phoneNumber;
    private String detailAddress;
    private String streetAddress;
    private List<String> roles;
    private int zipCode;

    public static MemberResponse from(Member member) {
        // roles가 null일 수 있으므로 빈 리스트로 초기화
        List<String> roleList = Optional.ofNullable(member.getRoles())
                .orElse(Collections.emptyList())
                .stream()
                .map(Role::getRole)
                .collect(Collectors.toList());

        // address가 null일 수 있으므로 기본값 처리
        Address address = member.getAddress();
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .username(member.getUsername())
                .phoneNumber(member.getPhoneNumber())
                .detailAddress(address != null ? address.getDetailAddress() : "")
                .streetAddress(address != null ? address.getStreetAddress() : "")
                .roles(roleList)
                .zipCode(address != null ? address.getZipcode() : 00000)
                .build();
    }
}
