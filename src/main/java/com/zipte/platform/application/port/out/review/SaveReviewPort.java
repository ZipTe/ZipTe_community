package com.zipte.platform.application.port.out.review;

import com.zipte.platform.domain.review.Review;

public interface SaveReviewPort {

    Review saveReview(Review review);

}
