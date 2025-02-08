package com.eedo.project.zipte.application.service.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.representation.request.order.DeliveryRequest;
import com.eedo.project.zipte.application.port.in.DeliveryService;
import com.eedo.project.zipte.representation.response.DeliveryResponse;
import com.eedo.project.zipte.domain.order.Delivery;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.order.DeliveryRepository;
import com.eedo.project.zipte.domain.payment.Payment;
import com.eedo.project.zipte.infrastructure.out.persistence.jpa.payment.PaymentRepository;
import com.eedo.project.zipte.domain.user.Address;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@RequiredArgsConstructor
@Transactional
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public DeliveryResponse updateAddress(DeliveryRequest deliveryRequest) {

        Payment payment = paymentRepository.findByTossOrderId(deliveryRequest.getTossOrderId())
                .orElseThrow(() -> new EntityNotFoundException("조회할 결제 내역이 없습니다."));
        Delivery delivery = payment.getOrder().getDelivery();

        // 주소 업데이트
        if (deliveryRequest.getStreetAddress() != null) {

            Address address = Address.of(deliveryRequest.getStreetAddress(), deliveryRequest.getDetailAddress(), deliveryRequest.getZipCode());
            delivery.changeAddress(address);
        }

        // 설명 업데이트
        if (deliveryRequest.getDeliveryDesc() != null) {
                delivery.changeDeliveryDesc(deliveryRequest.getDeliveryDesc());
        }if (deliveryRequest.getOrderDesc() != null) {
                delivery.changeOrderDesc(deliveryRequest.getOrderDesc());
        }

        // 변경된 Delivery 객체 저장
        Delivery save = deliveryRepository.save(delivery);

        return DeliveryResponse.from(save);
    }

    @Override
    public DeliveryResponse updateStatus(DeliveryRequest deliveryRequest) {
        //        // Enum 값으로 비교하여 상태 업데이트
//        if (deliveryRequest.getStatus() != null) {  // 상태가 null이 아닌 경우에만 변경
//            if (deliveryRequest.getStatus() == DeliveryStatus.DELIVERING) {
//                delivery.deliveryIng();
//            } else if (deliveryRequest.getStatus() == DeliveryStatus.CANCEL) {
//                delivery.deliveryCancel();
//            } else if (deliveryRequest.getStatus() == DeliveryStatus.DELIVERY) {
//                delivery.deliveryComplete();
//            }
//        }

        return null;
    }

}
