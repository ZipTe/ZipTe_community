package org.gdg.zipte_gdg.api.controller.apartment.comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.apartment.comment.request.CommentRequest;
import org.gdg.zipte_gdg.api.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.apartment.comment.CommentService;
import org.gdg.zipte_gdg.api.service.apartment.comment.response.CommentResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/comment")
@Log4j2
public class CommentController {

    private final CommentService commentService;


    // 댓글 작성하기
    @PostMapping
    ApiResponse<CommentResponse> register(@RequestBody CommentRequest commentRequest) {
        return ApiResponse.ok(commentService.register(commentRequest));
    }

    // 댓글 수정하기


    // 댓글 삭제하기
}
