package org.gdg.zipte_gdg.api.controller.cart;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte_gdg.api.controller.cart.request.CartRequestDto;
import org.gdg.zipte_gdg.api.controller.order.request.OrderRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.cart.CartService;
import org.gdg.zipte_gdg.api.service.cart.response.CartResponseDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping()
    public ApiResponse<CartResponseDto> addItem(@RequestBody CartRequestDto cartRequestDto) {

        return ApiResponse.created(cartService.setItem(cartRequestDto));
    }

    @DeleteMapping()
    public ApiResponse<CartResponseDto> removeItem(@RequestBody CartRequestDto cartRequestDto) {

        return ApiResponse.created(cartService.removeItem(cartRequestDto));
    }

    @GetMapping("/{memberId}")
    public ApiResponse<CartResponseDto> getOne(@PathVariable Long memberId) {
        return ApiResponse.created(cartService.getMyCart(memberId));
    }

    @GetMapping("/order")
    public OrderRequestDto orderFromCart(@RequestBody CartRequestDto cartRequestDto) {
        return cartService.orderCartItem(cartRequestDto);
    }

    // OAUTH적용할 때

    //    @GetMapping("/myCart")
//    public ApiResponse<CartResponseDto> addItem(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//        Long memberId = principalDetails.getId();
//        return ApiResponse.created(cartService.getMyCart(memberId));
//    }


}
