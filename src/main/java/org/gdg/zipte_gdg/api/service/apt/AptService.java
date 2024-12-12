package org.gdg.zipte_gdg.api.service.apt;

import org.gdg.zipte_gdg.api.controller.apt.request.AptRequestDto;
import org.gdg.zipte_gdg.api.service.apt.response.AptResposnseDto;
import reactor.core.publisher.Mono;

public interface AptService {

    // 생성
    AptResposnseDto register (AptRequestDto aptRequestDto);

    // 찾기
//    AptResposnseDto getOne(Long id);
    Mono<Object> getOne(String kaptCode);
    // 평점 높은 아파트 찾기

    // 아파트 목록 찾기 <특정 동의 아파트 찾기>

    // 아파트 목록 찾기 <특정 지하철의 아파트 찾기>

    // AI 활용하여 비슷한 아파트 추천 가져오기 구현하기
    Mono<Object> getListByAI(String apartment_name, int top_n);

    // 동에 따른 1년간 평당 가격 추이
    Mono<Object> getPriceByDong(String dong, int year);

    // 동, 평에 따른 평당 가격 추이
    Mono<Object> getPriceByDongAndSize(String dong,String size, int year);

    // 아파트 따른 평당 가격 추이
    Mono<Object> getPriceByApt(String apt_name, String size, int year);

}
