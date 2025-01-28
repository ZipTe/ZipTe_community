package com.eedo.project.zipte.infrastructure.out.persistence.jpa.order;

import com.eedo.project.zipte.domain.order.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
