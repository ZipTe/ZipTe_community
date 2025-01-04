package org.gdg.zipte.api.controller.board.commentLike;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.commentLike.request.CommentReactionRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.commentReaction.CommentReactionService;
import org.gdg.zipte.api.service.board.commentReaction.response.CommentReactionResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/reaction")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping
    public ApiResponse<CommentReactionResponse> create(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CommentReactionRequest request) {
        request.setMemberId(principalDetails.getId());
        return ApiResponse.created(commentReactionService.create(request));
    }

    @DeleteMapping
    public ApiResponse<CommentReactionResponse> delete(@AuthenticationPrincipal PrincipalDetails principalDetails , @RequestBody CommentReactionRequest request) {
        request.setMemberId(principalDetails.getId());
        return ApiResponse.created(commentReactionService.delete(request));
    }

}
