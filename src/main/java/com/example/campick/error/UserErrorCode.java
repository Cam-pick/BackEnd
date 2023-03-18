package com.example.campick.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserErrorCode {
    USER_NOT_FOUND_EXCEPTION(600,"USER-ERROR-600","USER NOT FOUND IN DB"),
    USER_INDEX_INVALID_EXCEPTION(601, "USER-ERROR-601", "INVALID USER INDEX"),
    INVALID_USER_ID(602, "USER-ERROR-602", "INVALID USER ID"),
    INVALID_USER_PWD(603, "USER-ERROR-603", "INVALID USER PWD"),
    ILLEGAL_INPUT(604, "USER-ERROR-604", "ILLEGAL INPUT(REGEX ERROR OR NULL)"),
    DUPLICATE_INPUT(605, "USER-ERROR-605", "DUPLICATE INPUT");


    private int status;
    private String errorCode;
    private String message;
}
