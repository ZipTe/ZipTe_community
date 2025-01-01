package org.gdg.zipte_gdg.api.controller.shopping.cart;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.shopping.cart.request.CartRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.shopping.cart.CartService;
import org.gdg.zipte_gdg.api.service.shopping.cart.response.CartResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping()
    public ApiResponse<CartResponse> addItem(@RequestBody CartRequest cartRequest) {

        return ApiResponse.created(cartService.setItem(cartRequest));
    }

    @DeleteMapping()
    public ApiResponse<CartResponse> removeItem(@RequestBody CartRequest cartRequest) {

        return ApiResponse.created(cartService.removeItem(cartRequest));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<CartResponse> getOne(@PathVariable Long memberId) {
        return ApiResponse.created(cartService.getMyCart(memberId));
    }

    // OAUTH적용할 때

    //    @GetMapping("/myCart")
//    public ApiResponse<CartResponseDto> addItem(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        Long memberId = principalDetails.getId();
//        return ApiResponse.created(cartService.getMyCart(memberId));
//    }


}
