package com.example.campick.controller;

import com.example.campick.error.UserErrorCode;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.jwt.JwtTokenProvider;
import com.example.campick.model.dto.JoinDto;
import com.example.campick.model.dto.LoginReqDto;
import com.example.campick.model.dto.LoginResDto;
import com.example.campick.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * 회원가입
     * [POST] "/users/signup"
     */
    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> join(@RequestBody @Valid JoinDto joinDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("Input regex error or null", UserErrorCode.ILLEGAL_INPUT);
        }
        loginService.join(joinDto);

        return ResponseEntity.ok().build();

    }

    /**
     * 로그인
     * [POST] "/users/login"
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody @Valid LoginReqDto loginReqDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new UserNotFoundException("Input Regex Error or Null Error", UserErrorCode.ILLEGAL_INPUT);
        }

        LoginResDto loginResDto = loginService.login(loginReqDto);
        return ResponseEntity.ok(loginResDto);

    }

    /**
     * 회원탈퇴
     * [PATCH] "/users/:userIdx/d"
     */
    @PatchMapping("/{userIdx}/d")
    @ResponseBody
    public ResponseEntity<?> resign(@PathVariable Long userIdx, ServletRequest request) throws Exception {

        String token = jwtTokenProvider.resolveAccessToken((HttpServletRequest) request);
        Long userPK = Long.valueOf(jwtTokenProvider.getUserPK(token));

        // 탈퇴 API 사용자와 탈퇴 대상 회원이 다른 경우 에러 처리
        if (userPK != userIdx) {
            throw new UserNotFoundException("Invalid User Index", UserErrorCode.USER_INDEX_INVALID_EXCEPTION);
        }

        loginService.resign(userIdx);

        return ResponseEntity.ok("회원 탈퇴에 성공하였습니다.");
    }
}