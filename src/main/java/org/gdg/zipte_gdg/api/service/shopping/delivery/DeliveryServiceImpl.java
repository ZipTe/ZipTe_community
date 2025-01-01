package org.gdg.zipte_gdg.api.service.shopping.delivery;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.shopping.delivery.request.DeliveryRequest;
import org.gdg.zipte_gdg.api.service.shopping.delivery.response.DeliveryResponse;
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
    public DeliveryResponse findById(Long id) {
        Delivery delivery = deliveryRepository.findById(id).orElseThrow();
        return DeliveryResponse.of(delivery);
    }

    @Override
    public DeliveryResponse updateOne(DeliveryRequest deliveryRequest) {
        Delivery delivery = deliveryRepository.findById(deliveryRequest.getId()).orElseThrow();

        // Enum 값으로 비교하여 상태 업데이트
        if (deliveryRequest.getStatus() != null) {  // 상태가 null이 아닌 경우에만 변경
            if (deliveryRequest.getStatus() == DeliveryStatus.DELIVERING) {
                delivery.deliveryIng();
            } else if (deliveryRequest.getStatus() == DeliveryStatus.CANCEL) {
                delivery.deliveryCancel();
            } else if (deliveryRequest.getStatus() == DeliveryStatus.DELIVERY) {
                delivery.deliveryComplete();
            }
        }

        // 주소 업데이트
        if (deliveryRequest.getAddress() != null) {
            Address address = Address.builder()
                    .streetAddress(deliveryRequest.getAddress().getStreetAddress() != null ? deliveryRequest.getAddress().getStreetAddress() : delivery.getAddress().getStreetAddress())
                    .detailAddress(deliveryRequest.getAddress().getDetailAddress() != null ? deliveryRequest.getAddress().getDetailAddress() : delivery.getAddress().getDetailAddress())
                    .zipcode(deliveryRequest.getAddress().getZipCode() != 0 ? deliveryRequest.getAddress().getZipCode() : delivery.getAddress().getZipcode())
                    .build();
            delivery.changeAddress(address);
        }

        // 설명 업데이트
        if (deliveryRequest.getDescription() != null) {
            if (deliveryRequest.getDescription().getDeliveryDesc() != null) {
                delivery.changeDeliveryDesc(deliveryRequest.getDescription().getDeliveryDesc());
            }
            if (deliveryRequest.getDescription().getOrderDesc() != null) {
                delivery.changeOrderDesc(deliveryRequest.getDescription().getOrderDesc());
            }
        }

        // 변경된 Delivery 객체 저장
        Delivery save = deliveryRepository.save(delivery);

        return DeliveryResponse.of(save);
    }

}
