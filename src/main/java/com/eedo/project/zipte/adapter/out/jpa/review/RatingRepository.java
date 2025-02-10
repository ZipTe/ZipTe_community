package com.eedo.project.zipte.adapter.out.jpa.review;

import com.eedo.project.zipte.domain.review.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {


}
