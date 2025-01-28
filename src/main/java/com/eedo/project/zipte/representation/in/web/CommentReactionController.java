package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import com.eedo.project.zipte.representation.request.board.CommentReactionRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CommentReactionService;
import com.eedo.project.zipte.representation.response.CommentReactionResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
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
