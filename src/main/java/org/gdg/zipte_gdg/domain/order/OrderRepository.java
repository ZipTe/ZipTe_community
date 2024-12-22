package org.gdg.zipte_gdg.domain.order;

import org.gdg.zipte_gdg.domain.orderItem.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //TossOrderId로 조회
    Optional<Order> findByTossOrderId(String tossOrderId);

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
    List<OrderItem> findOrderItemsByOrderId(@Param("orderId") Long orderId);


    @Query("select o from Order o where o.member.id =:memberId ")
    Page<Order> findOrdersByMemberId(@Param("memberId") Long memberId, Pageable pageable);
}
