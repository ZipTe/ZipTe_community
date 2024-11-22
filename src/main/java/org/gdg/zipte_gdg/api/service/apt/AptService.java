package org.gdg.zipte_gdg.api.service.apt;

import org.gdg.zipte_gdg.api.controller.apt.request.AptRequestDto;
import org.gdg.zipte_gdg.api.service.apt.response.AptResposnseDto;

public interface AptService {

    // 생성
    AptResposnseDto register (AptRequestDto aptRequestDto);

    // 찾기
    AptResposnseDto getOne(Long id);

    // 아파트 목록 찾기 <특정동의 아파트 찾기>

    // 아파트 목록 찾기 <특정지하철의 아파트 찾기>

    // 아파트 관련 리뷰 찾기

    // 평점 높은 리뷰 찾기



}
