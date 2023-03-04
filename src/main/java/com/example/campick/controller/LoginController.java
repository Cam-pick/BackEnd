package com.example.campick.controller;

import com.example.campick.config.security.JwtTokenProvider;
import com.example.campick.model.dto.PostJoinReq;
import com.example.campick.model.dto.PostJoinRes;
import com.example.campick.model.dto.PostLoginReq;
import com.example.campick.model.dto.PostLoginRes;
import com.example.campick.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public ResponseEntity<?> join(@RequestBody @Valid PostJoinReq postJoinReq, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        PostJoinRes postJoinRes = loginService.join(postJoinReq);
        return ResponseEntity.ok(postJoinRes);

    }

    /**
     * 로그인
     * [POST] "/users/login"
     */
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody @Valid PostLoginReq postLoginReq, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        PostLoginRes postLoginRes = loginService.login(postLoginReq);
        return ResponseEntity.ok(postLoginRes);

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
        if(userPK != userIdx) {
            throw new Exception();
        }
        loginService.resign(userIdx);
        return ResponseEntity.ok("회원 탈퇴에 성공하였습니다.");
    }
}
