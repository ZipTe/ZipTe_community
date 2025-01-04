package org.gdg.zipte.api.controller.board.like;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.like.request.BoardReactionRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.boardReaction.BoardReactionService;
import org.gdg.zipte.api.service.board.boardReaction.response.BoardReactionResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
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
