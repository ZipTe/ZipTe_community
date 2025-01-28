package com.eedo.project.zipte.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class SavedAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Embedded
    private Address address;

    // 기본 배송지 여부
    private boolean isDefault;

    private String orderDesc;

    private String deliveryDesc;

    //생성자
    public static SavedAddress of(Member member, Address address, String orderDesc, String deliveryDesc, boolean isDefault) {
        return SavedAddress.builder()
                .member(member)
                .address(address)
                .deliveryDesc(deliveryDesc)
                .orderDesc(orderDesc)
                .isDefault(isDefault)
                .build();
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

}
