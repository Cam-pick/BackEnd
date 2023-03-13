package com.example.campick.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    USER_NOT_FOUND_EXCEPTION(400,"USER-ERROR-400","USER NOT FOUND IN DB");

    private int status;
    private String errorCode;
    private String message;
}
