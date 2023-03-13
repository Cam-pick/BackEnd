package com.example.campick.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PostErrorCode {
    POST_NOT_FOUND_EXCEPTION(400,"POST-ERROR-400","POST NOT FOUND IN DB");

    private int status;
    private String errorCode;
    private String message;
}
