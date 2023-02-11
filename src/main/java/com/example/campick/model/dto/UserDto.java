package com.example.campick.model.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long userIdx;

    private String uniqueId;

    private String password;

    private String nickname;

    private String introduce;

    private String email;

    private String imageUrl;

    @Builder
    public UserDto(Long userIdx, String uniqueId, String password, String nickname, String introduce, String email, String imageUrl) {
        this.userIdx = userIdx;
        this.uniqueId = uniqueId;
        this.password = password;
        this.nickname = nickname;
        this.introduce = introduce;
        this.email = email;
        this.imageUrl = imageUrl;
    }
}
