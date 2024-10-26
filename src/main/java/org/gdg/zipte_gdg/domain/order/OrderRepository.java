package org.gdg.zipte_gdg.domain.order;

import org.gdg.zipte_gdg.domain.orderItem.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("SELECT oi FROM OrderItem oi WHERE oi.order.id = :orderId")
    List<OrderItem> findOrderItemsByOrderId(@Param("orderId") Long orderId);
}
