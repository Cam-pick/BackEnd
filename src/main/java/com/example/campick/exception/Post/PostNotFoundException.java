package com.example.campick.exception.Post;

import com.example.campick.error.PostErrorCode;
import lombok.Getter;

@Getter
public class PostNotFoundException extends RuntimeException{

    private PostErrorCode postErrorCode;
    public PostNotFoundException(String msg, PostErrorCode postErrorCode){
        super(msg);
        this.postErrorCode = postErrorCode;
    }

}
