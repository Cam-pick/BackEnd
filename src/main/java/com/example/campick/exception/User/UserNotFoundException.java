package com.example.campick.exception.User;

import com.example.campick.error.UserErrorCode;
import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException{

    private UserErrorCode userErrorCode;
    public UserNotFoundException(String msg, UserErrorCode userErrorCode){
        super(msg);
        this.userErrorCode = userErrorCode;
    }

}
