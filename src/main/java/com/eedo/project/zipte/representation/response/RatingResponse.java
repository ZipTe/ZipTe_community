package com.eedo.project.zipte.representation.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RatingResponse {

    // 교통 여건 평점
    private int transportConditionRating;

    // 주변 환경 평점
    private int environmentConditionRating;

    // 단지 관리 평점
    private int apartmentManagementRating;

    // 거주 환경 평점
    private int livingEnvironmentRating;

    // 전체 평점
    private int overallRating;

}
