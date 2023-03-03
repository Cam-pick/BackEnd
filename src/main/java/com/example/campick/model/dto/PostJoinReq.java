package com.example.campick.model.dto;

import com.example.campick.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostJoinReq {
    private String uniqueId;
    private String password;
    private String nickname;
    private String introduce;
    private String email;
}
