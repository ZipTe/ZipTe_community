package org.gdg.zipte_gdg.api.controller.shopping.cart;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.cart.CartService;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;
import org.gdg.zipte_gdg.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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
    public ApiResponse<CartResponse> removeItem(@AuthenticationPrincipal PrincipalDetails principalDetails,@RequestBody CartRequest cartRequest) {
        cartRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(cartService.removeItem(cartRequest));
    }

    // 나의 장바구니 조회
    @GetMapping("/myCart")
    public ApiResponse<CartResponse> getmyCart(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long memberId = principalDetails.getId();
        return ApiResponse.created(cartService.getMyCart(memberId));
    }

}
