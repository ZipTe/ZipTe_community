package com.eedo.project.zipte.representation.in.web.admin;

import lombok.RequiredArgsConstructor;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CartService;
import com.eedo.project.zipte.representation.response.CartResponse;
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
