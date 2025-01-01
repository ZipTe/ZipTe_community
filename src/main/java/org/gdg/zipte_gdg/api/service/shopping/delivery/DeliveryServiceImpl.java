package org.gdg.zipte_gdg.api.service.shopping.delivery;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.shopping.delivery.request.DeliveryUpdateDto;
import org.gdg.zipte_gdg.api.service.shopping.delivery.response.DeliveryResponseDto;
import org.gdg.zipte_gdg.domain.shopping.delivery.Delivery;
import org.gdg.zipte_gdg.domain.shopping.delivery.DeliveryRepository;
import org.gdg.zipte_gdg.domain.shopping.delivery.DeliveryStatus;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    @Override
    public DeliveryResponseDto findById(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        return entityToDto(delivery);
    }

    @Override
    public DeliveryResponseDto updateOne(DeliveryUpdateDto deliveryUpdateDto) {
        Delivery delivery = deliveryRepository.findById(deliveryUpdateDto.getId()).orElseThrow();

        // Enum 값으로 비교하여 상태 업데이트
        if (deliveryUpdateDto.getStatus() != null) {  // 상태가 null이 아닌 경우에만 변경
            if (deliveryUpdateDto.getStatus() == DeliveryStatus.DELIVERING) {
                delivery.deliveryIng();
            } else if (deliveryUpdateDto.getStatus() == DeliveryStatus.CANCEL) {
                delivery.deliveryCancel();
            } else if (deliveryUpdateDto.getStatus() == DeliveryStatus.DELIVERY) {
                delivery.deliveryComplete();
            }
        }

        // 주소 업데이트
        if (deliveryUpdateDto.getAddress() != null) {
            Address address = Address.builder()
                    .streetAddress(deliveryUpdateDto.getAddress().getStreetAddress() != null ? deliveryUpdateDto.getAddress().getStreetAddress() : delivery.getAddress().getStreetAddress())
                    .detailAddress(deliveryUpdateDto.getAddress().getDetailAddress() != null ? deliveryUpdateDto.getAddress().getDetailAddress() : delivery.getAddress().getDetailAddress())
                    .zipcode(deliveryUpdateDto.getAddress().getZipCode() != 0 ? deliveryUpdateDto.getAddress().getZipCode() : delivery.getAddress().getZipcode())
                    .build();
            delivery.changeAddress(address);
        }

        // 설명 업데이트
        if (deliveryUpdateDto.getDescription() != null) {
            if (deliveryUpdateDto.getDescription().getDeliveryDesc() != null) {
                delivery.changeDeliveryDesc(deliveryUpdateDto.getDescription().getDeliveryDesc());
            }
            if (deliveryUpdateDto.getDescription().getOrderDesc() != null) {
                delivery.changeOrderDesc(deliveryUpdateDto.getDescription().getOrderDesc());
            }
        }

        // 변경된 Delivery 객체 저장
        Delivery save = deliveryRepository.save(delivery);

        return entityToDto(save);
    }

}
