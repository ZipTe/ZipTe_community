package org.gdg.zipte.api.controller.user.savedAddress;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.user.savedAddress.request.SavedAddressRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.user.savedAddress.SavedAddressService;
import org.gdg.zipte.api.service.user.savedAddress.response.SavedAddressResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
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
        return ApiResponse.created(savedAddressService.findById(principalDetails.getId()));
    }

    // 특정 유저
    @GetMapping("member/{memberId}")
    ApiResponse<List<SavedAddressResponse>> findList (@PathVariable Long memberId) {
        return ApiResponse.created(savedAddressService.findById(memberId));
    }

    // 주소지 하나만 가져오기
    @GetMapping("/{id}")
    ApiResponse<SavedAddressResponse> findById (@PathVariable Long id) {
        return ApiResponse.created(savedAddressService.getOne(id));
    }

}
