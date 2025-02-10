package com.eedo.project.zipte.adapter.in.api;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.adapter.in.api.dto.request.board.BoardReactionRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.board.BoardReactionService;
import com.eedo.project.zipte.adapter.in.api.dto.response.BoardReactionResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
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
