package com.eedo.project.zipte.infrastructure.out.persistence.jpa.review;

import com.eedo.project.zipte.domain.review.ReviewImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {

    
}
