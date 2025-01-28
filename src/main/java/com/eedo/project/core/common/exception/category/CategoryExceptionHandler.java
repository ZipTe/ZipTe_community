package com.eedo.project.core.common.exception.category;

import lombok.extern.log4j.Log4j2;
import com.eedo.project.core.common.ApiResponse;
import com.eedo.project.core.common.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.eedo.project.core.common.ErrorCode.DUPLICATE_ERROR;

@Log4j2
@RestControllerAdvice
public class CategoryExceptionHandler {

    // 코드가 중복될 때, 예외 처리
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ApiResponse<?> handleDuplicateError(DuplicateCodeException e) {
        log.error("DuplicateCodeException 발생: {}", e.getMessage(), e);
        return ApiResponse.fail(new CustomException(DUPLICATE_ERROR));
    }



}
