package com.example.campick.model.dto.error;

import com.example.campick.error.PostErrorCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostErrorResponse {
    private int status;
    private String message;
    private String code;

    public PostErrorResponse(PostErrorCode errorCode){
        this.status = errorCode.getStatus();
        this.message = errorCode.getMessage();
        this.code = errorCode.getErrorCode();
    }
}
