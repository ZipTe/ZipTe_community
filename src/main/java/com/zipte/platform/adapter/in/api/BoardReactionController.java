package com.zipte.platform.adapter.in.api;

import lombok.RequiredArgsConstructor;
import com.zipte.platform.adapter.in.api.dto.request.board.BoardReactionRequest;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.application.port.in.board.BoardReactionService;
import com.zipte.platform.adapter.in.api.dto.response.BoardReactionResponse;
import com.zipte.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reaction")
@RequiredArgsConstructor
public class BoardReactionController {

    private final BoardReactionService boardReactionService;

    @PostMapping
    public ApiResponse<BoardReactionResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardReactionRequest request) {
        request.setMemberId(principalDetails.getId());
        return ApiResponse.created(boardReactionService.create(request));
    }

    @DeleteMapping
    public ApiResponse<BoardReactionResponse> delete(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardReactionRequest request) {
        request.setMemberId(principalDetails.getId());
        return ApiResponse.created(boardReactionService.delete(request));
    }

}
