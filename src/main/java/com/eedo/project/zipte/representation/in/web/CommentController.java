package com.eedo.project.zipte.representation.in.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import com.eedo.project.zipte.representation.request.board.CommentRequest;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.zipte.application.port.in.CommentService;
import com.eedo.project.zipte.representation.response.CommentResponse;
import com.eedo.project.core.common.page.request.PageRequest;
import com.eedo.project.core.common.page.response.PageResponse;
import com.eedo.project.core.security.oauth.domain.PrincipalDetails;
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
