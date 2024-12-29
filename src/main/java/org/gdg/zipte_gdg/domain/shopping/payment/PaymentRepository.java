package org.gdg.zipte_gdg.domain.shopping.payment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    @Query("SELECT p FROM Payment p LEFT JOIN p.order o WHERE o.member.id =:memberId AND p.tossOrderId = o.tossOrderId")
    Page<Payment> getMyAllPayment(@Param("memberId") Long memberId,Pageable pageable);


}
