package com.zipte.platform.adapter.out.jpa.review;

import com.zipte.platform.domain.review.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {


}
