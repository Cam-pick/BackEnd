package com.example.campick.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PartyErrorCode {

    PARTY_INDEX_INVALID_EXCEPTION(300,"PARTY-ERROR-300","INVALID PARTY INDEX"),
    ILLEGAL_INPUT(301, "PARTY-ERROR-301", "ILLEGAL INPUT(REGEX ERROR OR NULL)"),
    NULL_IMAGE_FILE(302, "PARTY_ERROR-302", "NULL IMAGE FILE");


    private int status;
    private String errorCode;
    private String message;

}
