package com.zipte.platform.adapter.in.api;

import com.zipte.platform.application.port.in.board.AddReactionUseCase;
import com.zipte.platform.application.port.in.board.RemoveReactionUseCase;
import com.zipte.platform.application.port.in.dto.request.board.BoardReactionRequest;
import lombok.RequiredArgsConstructor;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.adapter.in.api.dto.response.BoardReactionResponse;
import com.zipte.core.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reaction")
@RequiredArgsConstructor
public class BoardReactionController {

    private final AddReactionUseCase addService;
    private final RemoveReactionUseCase removeService;

    @PostMapping
    public ApiResponse<BoardReactionResponse> addOne(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardReactionRequest request) {
        request.setMemberId(principalDetails.getId());

        return ApiResponse.created(BoardReactionResponse.from(addService.addReaction(request)));
    }

    @DeleteMapping
    public ApiResponse<String> removeOne(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody BoardReactionRequest request) {
        request.setMemberId(principalDetails.getId());
        removeService.removeReaction(request);

        return ApiResponse.created("성공적으로 삭제되었습니다");
    }

}
