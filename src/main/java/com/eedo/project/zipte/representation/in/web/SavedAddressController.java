package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.user.SavedAddressRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.SavedAddressService;
import com.eedo.project.zipte.representation.response.SavedAddressResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/deliveryList")
@RequiredArgsConstructor
public class SavedAddressController {

    private final SavedAddressService savedAddressService;

    // 주소지 추가하기
    @PostMapping
    ApiResponse<SavedAddressResponse> create (@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody SavedAddressRequest request) {
        request.setMemberId(principalDetails.getId());
        return ApiResponse.created(savedAddressService.create(request));
    }

    // 나의 주소 목록 가져오기
    @GetMapping("/myPage")
    ApiResponse<List<SavedAddressResponse>> findMyList (@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return ApiResponse.ok(savedAddressService.findById(principalDetails.getId()));
    }

    // 주소지 하나만 가져오기
    @GetMapping("/{id}")
    ApiResponse<SavedAddressResponse> findById (@PathVariable Long id) {
        return ApiResponse.ok(savedAddressService.getOne(id));
    }

    // 특정 유저
    @GetMapping("member/{memberId}")
    ApiResponse<List<SavedAddressResponse>> findList (@PathVariable Long memberId) {
        return ApiResponse.ok(savedAddressService.findById(memberId));
    }

}
