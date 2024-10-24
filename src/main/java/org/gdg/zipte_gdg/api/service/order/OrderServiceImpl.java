package org.gdg.zipte_gdg.api.service.order;

import lombok.RequiredArgsConstructor;

import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.service.delivery.response.DeliveryDto;
import org.gdg.zipte_gdg.api.service.order.response.OrderResponseDto;
import org.gdg.zipte_gdg.domain.delivery.Delivery;
import org.gdg.zipte_gdg.domain.delivery.DeliveryRepository;
import org.gdg.zipte_gdg.domain.member.Address;
import org.gdg.zipte_gdg.domain.member.Member;
import org.gdg.zipte_gdg.domain.member.MemberRepository;
import org.gdg.zipte_gdg.domain.order.Order;
import org.gdg.zipte_gdg.domain.order.OrderRepository;
import org.gdg.zipte_gdg.domain.product.OrderItem;
import org.gdg.zipte_gdg.domain.product.Product;
import org.gdg.zipte_gdg.domain.product.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryRepository deliveryRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Override
    public OrderResponseDto registerOrder(OrderRequestDto orderRequestDto) {

        Member member = getMember(orderRequestDto);
        Address address = getAddress(orderRequestDto);
        Delivery delivery = getDelivery(orderRequestDto, address);
        List<OrderItem> orderItems = getOrderItems(orderRequestDto);

        // 누가,어디로,무엇을 살것인지 정했기에 Order 가능
        Order order = Order.createNewOrder(member, delivery, orderItems);
        orderRepository.save(order);
        return entityToDTO(order);
    }


    @Override
    public OrderResponseDto findById(Long id) {
        return null;
    }

    @Override
    public Order updateAddress(Long id, DeliveryDto dto) {
        return null;
    }



    //extract한 메소드

    private Member getMember(OrderRequestDto orderRequestDto) {
        Optional<Member> byId = memberRepository.findById(orderRequestDto.getMemberId());
        return byId.orElseThrow();
    }

    private static Address getAddress(OrderRequestDto orderRequestDto) {
        return Address.newAddress(orderRequestDto.getCity(), orderRequestDto.getStreetAddress(), orderRequestDto.getZipcode());
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
