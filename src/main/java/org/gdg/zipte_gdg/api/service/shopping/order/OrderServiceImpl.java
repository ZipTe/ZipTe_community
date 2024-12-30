package org.gdg.zipte_gdg.api.service.shopping.order;

import lombok.RequiredArgsConstructor;

import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.order.response.PaymentOrderResponseDto;
import org.gdg.zipte_gdg.domain.shopping.delivery.Delivery;
import org.gdg.zipte_gdg.domain.shopping.delivery.DeliveryRepository;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManager;
import org.gdg.zipte_gdg.domain.shopping.productManger.ProductManagerRepository;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.gdg.zipte_gdg.domain.user.member.Member;
import org.gdg.zipte_gdg.domain.user.member.MemberRepository;
import org.gdg.zipte_gdg.domain.shopping.order.Order;
import org.gdg.zipte_gdg.domain.shopping.order.OrderRepository;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItem;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Date;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
    private final ProductManagerRepository productManagerRepository;

    @Override
    public PaymentOrderResponseDto order(OrderRequestDto orderRequestDto) {

        Member member = getMember(orderRequestDto);
        Address address = getAddress(orderRequestDto);
        Delivery delivery = getDelivery(orderRequestDto, address);
        List<OrderItem> orderItems = getOrderItems(orderRequestDto);

        orderItemRepository.saveAll(orderItems);

        // 누가,어디로,무엇을 살것인지 정했기에 Order 가능
        Order order = Order.createNewOrder(member, delivery, orderItems);
        orderRepository.save(order);
        return entityToPaymentDTO(order);
    }

    //extract한 메소드

    private Member getMember(OrderRequestDto orderRequestDto) {
        Optional<Member> byId = memberRepository.findById(orderRequestDto.getMemberId());
        return byId.orElseThrow();
    }

    private static Address getAddress(OrderRequestDto orderRequestDto) {
        return Address.newAddress(orderRequestDto.getDetailAddress(), orderRequestDto.getStreetAddress(), orderRequestDto.getZipcode());
    }

    private Delivery getDelivery(OrderRequestDto orderRequestDto, Address address) {
        Delivery delivery = Delivery.createNewDelivery(address, orderRequestDto.getOrderDesc(), orderRequestDto.getDeliveryDesc());
        return deliveryRepository.save(delivery);
    }

    private List<OrderItem> getOrderItems(OrderRequestDto orderRequestDto) {
        return orderRequestDto.getItems().stream()
                .map(itemDto -> {
                    ProductManager productManager = getProduct(itemDto.getProductId());
                    return OrderItem.createOrderItem(productManager, itemDto.getCount());
                })
                .toList();
    }

    private ProductManager getProduct(Long id) {
        // 상품 매니저가 존재하는지 체크
        ProductManager productManager = productManagerRepository.findByProductId(id);

        if (productManager != null) {
            // 판매 종료일이 오늘 날짜보다 이전이면, 해당 매니저는 비활성화하고, 다른 매니저를 다시 검색
            Date saleEndDate = productManager.getSaleEndDate();
            if (saleEndDate != null) {
                // Date를 LocalDate로 변환
                LocalDate saleEndLocalDate = saleEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                // 판매 종료일이 오늘 날짜보다 이전이면
                if (saleEndLocalDate.isBefore(LocalDate.now())) {
                    // 해당 매니저는 비활성화
                    productManager.setActive(false);
                    productManagerRepository.save(productManager);  // 변경된 내용을 저장

                    // 새로운 매니저를 다시 검색
                    ProductManager newProductManager = productManagerRepository.findByProductId(id);

                    // 새로운 매니저가 있으면 판매 종료일이 유효하면 반환
                    if (newProductManager != null && newProductManager.getSaleEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(LocalDate.now())) {
                        return newProductManager;
                    }

                    // 새로운 매니저가 없으면 기본 매니저를 찾고 활성화
                    ProductManager basicOne = productManagerRepository.findBasicOne(id);
                    if (basicOne != null) {
                        basicOne.setActive(true);
                        return productManagerRepository.save(basicOne);  // 기본 매니저를 활성화하고 저장
                    }
                }
            }
        }

        // 판매 종료일이 오늘 이후인 경우 기존 매니저를 그대로 반환
        return productManager;
    }
}
