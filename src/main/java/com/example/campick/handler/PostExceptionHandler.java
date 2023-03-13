package com.example.campick.handler;

import com.example.campick.exception.Post.PostNotFoundException;
import com.example.campick.model.dto.error.PostErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class PostExceptionHandler {

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<PostErrorResponse> postNotFoundException(PostNotFoundException e){
        log.error("postNotFoundException",e);
        PostErrorResponse response = new PostErrorResponse(e.getPostErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getPostErrorCode().getStatus()));
    }

}
