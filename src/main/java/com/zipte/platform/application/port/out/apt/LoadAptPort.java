package com.zipte.platform.application.port.out.apt;

import com.zipte.platform.domain.apt.Apt;

import java.util.Optional;

public interface LoadAptPort {

    // 하나의 아파트 가져오기
    Optional<Apt> loadApt(String id);

    // 특정 지역 아파트 가져오기

    // 위치 기반 반경 내 아파트 가져오기

}
