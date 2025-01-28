package com.eedo.project.zipte.application.port.in;

import com.eedo.project.zipte.representation.request.user.SavedAddressRequest;
import com.eedo.project.zipte.representation.response.SavedAddressResponse;

import java.util.*;

public interface SavedAddressService {

    // 기본 주소록 생성하기
    SavedAddressResponse create(SavedAddressRequest request);

    // 유저별 주소지 검색
    List<SavedAddressResponse> findById(Long memberId);

    // 특정 주소 하나만 검색
    SavedAddressResponse getOne(Long Id);


}
