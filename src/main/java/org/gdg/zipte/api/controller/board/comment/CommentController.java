package org.gdg.zipte.api.controller.board.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte.api.controller.board.comment.request.CommentRequest;
import org.gdg.zipte.api.response.ApiResponse;
import org.gdg.zipte.api.service.board.comment.CommentService;
import org.gdg.zipte.api.service.board.comment.response.CommentResponse;
import org.gdg.zipte.domain.page.request.PageRequest;
import org.gdg.zipte.domain.page.response.PageResponse;
import org.gdg.zipte.security.oauth.domain.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
@Log4j2
public class CommentController {

    private final CommentService commentService;


    // 댓글 작성하기
    @PostMapping
    ApiResponse<CommentResponse> register(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestBody CommentRequest commentRequest) {
        commentRequest.setMemberId(principalDetails.getId());
        return ApiResponse.created(commentService.register(commentRequest));
    }

    // 댓글 조회하기
    @GetMapping("/{boardId}")
    ApiResponse<PageResponse<CommentResponse>> read(@PathVariable("boardId") Long boardId, PageRequest pageRequest) {
        return ApiResponse.created(commentService.findAll(boardId, pageRequest));
    }

    // 댓글 삭제하기
}
