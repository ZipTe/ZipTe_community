package org.gdg.zipte_gdg.api.controller.admin.user;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.cart.CartService;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/user")
@RequiredArgsConstructor
public class AdminUserController {

    private final CartService cartService;

    // 특정 유저 장바구니 보기
    @GetMapping("/{memberId}")
    public ApiResponse<CartResponse> getOne(@PathVariable Long memberId) {
        return ApiResponse.created(cartService.getMyCart(memberId));
    }


    // 사용자 정지 시키기


    // 사용자 권한 업데이트

}
