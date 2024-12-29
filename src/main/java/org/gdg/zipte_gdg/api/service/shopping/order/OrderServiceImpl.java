package org.gdg.zipte_gdg.api.service.shopping.order;

import lombok.RequiredArgsConstructor;

import org.gdg.zipte_gdg.api.controller.shopping.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.service.shopping.order.response.PaymentOrderResponseDto;
import org.gdg.zipte_gdg.domain.shopping.delivery.Delivery;
import org.gdg.zipte_gdg.domain.shopping.delivery.DeliveryRepository;
import org.gdg.zipte_gdg.domain.user.member.Address;
import org.gdg.zipte_gdg.domain.user.member.Member;
import org.gdg.zipte_gdg.domain.user.member.MemberRepository;
import org.gdg.zipte_gdg.domain.shopping.order.Order;
import org.gdg.zipte_gdg.domain.shopping.order.OrderRepository;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItem;
import org.gdg.zipte_gdg.domain.shopping.orderItem.OrderItemRepository;
import org.gdg.zipte_gdg.domain.shopping.product.Product;
import org.gdg.zipte_gdg.domain.shopping.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

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
                    Product product = getProduct(itemDto.getProductId());
                    return OrderItem.createOrderItem(product, itemDto.getCount());
                })
                .toList();
    }

    private Product getProduct(Long id) {
        Optional<Product> byId1 = productRepository.findById(id);
        return byId1.orElseThrow();
    }
}
