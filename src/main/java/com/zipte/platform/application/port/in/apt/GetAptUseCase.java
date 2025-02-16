package com.zipte.platform.application.port.in.apt;

import com.zipte.platform.domain.apt.Apt;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface GetAptUseCase {

    // 아파트 정보 얻기
    Optional<Apt> getAptById(String id);

    // 평점이 높은 아파트 찾기
    Page<Apt> getAptByReview(Pageable pageable);

    // 특정 지역 아파트 목록 찾기


}
