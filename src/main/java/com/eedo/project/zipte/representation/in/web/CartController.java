package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.order.CartDeleteRequest;
import com.eedo.project.zipte.representation.request.order.CartRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CartService;
import com.eedo.project.zipte.representation.response.CartResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 아이템 추가
    @PostMapping()
    public ApiResponse<CartResponse> addItem(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody CartRequest cartRequest) {
        cartRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(cartService.setItem(cartRequest));
    }

    // 아이템 삭제
    @DeleteMapping()
    public ApiResponse<CartResponse> removeItem(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody CartDeleteRequest cartRequest) {
        cartRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(cartService.removeItem(cartRequest));
    }

    // 나의 장바구니 조회
    @GetMapping("/myCart")
    public ApiResponse<CartResponse> getmyCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getId();
        return ApiResponse.ok(cartService.getMyCart(memberId));
    }

}
