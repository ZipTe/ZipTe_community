package com.eedo.project.zipte.infrastructure.out.persistence.jpa.order;

import com.eedo.project.zipte.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    //TossOrderId로 조회
    Optional<Order> findByTossOrderId(String tossOrderId);

}
