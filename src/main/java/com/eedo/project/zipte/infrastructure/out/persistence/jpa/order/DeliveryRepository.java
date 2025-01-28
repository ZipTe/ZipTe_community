package com.eedo.project.zipte.infrastructure.out.persistence.jpa.order;

import com.eedo.project.zipte.domain.order.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery,Long> {
}
