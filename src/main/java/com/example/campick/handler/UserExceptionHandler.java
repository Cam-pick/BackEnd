package com.example.campick.handler;

import com.example.campick.exception.Post.PostNotFoundException;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.model.dto.error.PostErrorResponse;
import com.example.campick.model.dto.error.UserErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<UserErrorResponse> userNotFoundException(UserNotFoundException e){
        log.error("userNotFoundException",e);
        UserErrorResponse response = new UserErrorResponse(e.getUserErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getUserErrorCode().getStatus()));
    }

}
