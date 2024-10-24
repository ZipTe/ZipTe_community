package org.gdg.zipte_gdg.domain.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT r FROM Review r JOIN r.member m WHERE m.id = :memberId")
    Page<Review> findReviewsByMemberId(@Param("memberId") Long memberId, Pageable pageable);

}
