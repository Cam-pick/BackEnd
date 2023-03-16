package com.example.campick.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    USER_NOT_FOUND_EXCEPTION(400,"USER-ERROR-400","USER NOT FOUND IN DB"),
    USER_INDEX_INVALID_EXCEPTION(401, "USER-ERROR-401", "INVALID USER INDEX"),
    INVALID_USER_ID(402, "USER-ERROR-402", "INVALID USER ID"),
    INVALID_USER_PWD(403, "USER-ERROR-403", "INVALID USER PWD"),
    ILLEGAL_INPUT(404, "USER-ERROR-404", "ILLEGAL INPUT(REGEX ERROR OR NULL)"),
    DUPLICATE_INPUT(405, "USER-ERROR-405", "DUPLICATE INPUT");


    private int status;
    private String errorCode;
    private String message;
}
