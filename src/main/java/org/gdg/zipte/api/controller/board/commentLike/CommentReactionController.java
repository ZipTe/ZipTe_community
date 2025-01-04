package org.gdg.zipte.api.controller.board.commentLike;

import lombok.RequiredArgsConstructor;
import org.gdg.zipte.api.controller.board.commentLike.request.CommentReactionRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.commentLike.CommentReactionService;
import org.gdg.zipte.api.service.board.commentLike.response.CommentReactionResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment/reaction")
@RequiredArgsConstructor
public class CommentReactionController {

    private final CommentReactionService commentReactionService;

    @PostMapping
    public ApiResponse<CommentReactionResponse> create(@RequestBody CommentReactionRequest request) {
        return ApiResponse.created(commentReactionService.create(request));
    }

    @DeleteMapping
    public ApiResponse<CommentReactionResponse> delete(@RequestBody CommentReactionRequest request) {
        return ApiResponse.created(commentReactionService.delete(request));
    }

}
