package org.gdg.zipte_gdg.api.service.user.savedAddress.response;

import lombok.Builder;
import lombok.Data;
import org.gdg.zipte_gdg.domain.user.savedAddress.SavedAddress;

import java.util.*;

@Data
@Builder
public class SavedAddressResponse {

    private Long id;
    private Long memberId;
    private String detailAddress;
    private String streetAddress;
    private int zipcode;
    private String orderDesc;
    private String deliveryDesc;
    private Boolean isDefault;


    // 생성자
    public static SavedAddressResponse from(SavedAddress savedAddress) {
        return SavedAddressResponse.builder()
                .id(savedAddress.getId())
                .memberId(savedAddress.getMember().getId())
                .detailAddress(savedAddress.getAddress().getDetailAddress())
                .streetAddress(savedAddress.getAddress().getStreetAddress())
                .zipcode(savedAddress.getAddress().getZipcode())
                .orderDesc(savedAddress.getOrderDesc())
                .deliveryDesc(savedAddress.getDeliveryDesc())
                .isDefault(savedAddress.isDefault())
                .build();
    }

    public static List<SavedAddressResponse> froms(List<SavedAddress> savedAddresss) {

        List<SavedAddressResponse> responsesList = new ArrayList<>();

        savedAddresss.forEach(savedAddress -> {
            SavedAddressResponse savedAddressResponse = SavedAddressResponse.from(savedAddress);
            responsesList.add(savedAddressResponse);
        });

        return responsesList;
    }

}
