package com.example.campick.model.dto.error;

import com.example.campick.error.PostErrorCode;
import com.example.campick.error.UserErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserErrorResponse {
    private int status;
    private String message;
    private String code;

    public UserErrorResponse(UserErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
