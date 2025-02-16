package com.zipte.platform.application.port.in.review;

import com.zipte.platform.application.port.in.dto.request.review.ReviewRequest;
import com.zipte.platform.domain.review.Review;

public interface CreateReviewUseCase {

    Review createReview(ReviewRequest reviewRequest);


}
