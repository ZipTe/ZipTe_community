package com.eedo.project.zipte.infrastructure.out.persistence.jpa.order;

import com.eedo.project.zipte.domain.order.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {


}
