package org.gdg.zipte_gdg.api.controller.comment;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.gdg.zipte_gdg.api.controller.comment.request.CommentRequestDto;
import org.gdg.zipte_gdg.api.controller.response.ApiResponse;
import org.gdg.zipte_gdg.api.service.comment.CommentService;
import org.gdg.zipte_gdg.api.service.comment.response.CommentResponseDto;
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

    @PostMapping
    ApiResponse<CommentResponseDto> register(@RequestBody CommentRequestDto commentRequestDto) {
        return ApiResponse.ok(commentService.register(commentRequestDto));
    }

}
