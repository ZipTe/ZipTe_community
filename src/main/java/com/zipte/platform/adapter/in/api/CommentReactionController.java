package com.zipte.platform.adapter.in.api;

import com.zipte.platform.application.port.in.comment.AddLikeReactionUseCase;
import com.zipte.platform.application.port.in.comment.RemoveLikeReactionUseCase;
import com.zipte.platform.application.port.in.dto.request.board.CommentReactionRequest;
import lombok.RequiredArgsConstructor;
import com.zipte.core.common.ApiResponse;
import com.zipte.platform.adapter.in.api.dto.response.CommentReactionResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/reaction")
@RequiredArgsConstructor
public class CommentReactionController {

    private final AddLikeReactionUseCase addService;
    private final RemoveLikeReactionUseCase removeService;

    @PostMapping
    public ApiResponse<CommentReactionResponse> addOne(@RequestBody CommentReactionRequest request) {

        return ApiResponse.created(CommentReactionResponse.from(addService.addReaction(request)));
    }

    @DeleteMapping
    public ApiResponse<String> delete(@RequestBody CommentReactionRequest request) {
        removeService.removeReaction(request);
        return ApiResponse.created("성공적으로 삭제되었습니다.");
    }

}
