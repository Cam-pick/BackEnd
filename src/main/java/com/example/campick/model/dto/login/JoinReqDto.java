package com.example.campick.model.dto.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class JoinReqDto {
    @NotNull(message = "아이디는 필수 값입니다.")
    @Pattern(regexp = "^[a-z]+[a-z0-9]{3,20}$")
    private String uniqueId; // 4~20자, 영문자 소문자로 시작하고 영문자, 숫자 포함

    @NotNull(message = "비밀번호는 필수 값입니다.")
    @Pattern(regexp = "^.*(?=^.{8,15}$)(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$")
    private String password; // 8~15자, 숫자,영문자(대소문자),특수기호 포함

    @NotNull(message = "닉네임은 필수 값입니다.")
    @Pattern(regexp = "^[ㄱ-힣A-Za-z0-9]{3,20}$")
    private String nickname; // 4~20자, 영문자(대소문자), 한글, 숫자 포함

    private String introduce;

    @Pattern(regexp = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$")
    private String email;
}
