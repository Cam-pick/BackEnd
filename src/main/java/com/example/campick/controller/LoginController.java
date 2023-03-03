package com.example.campick.controller;

import com.example.campick.model.dto.PostJoinReq;
import com.example.campick.model.dto.PostJoinRes;
import com.example.campick.model.dto.PostLoginReq;
import com.example.campick.model.dto.PostLoginRes;
import com.example.campick.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    /**
     * 회원가입
     * [POST] "/signup"
     */
    @PostMapping("/signup")
    public ResponseEntity<?> join(@RequestBody PostJoinReq postJoinReq) {

        PostJoinRes postJoinRes = loginService.join(postJoinReq);
        return ResponseEntity.ok(postJoinRes);

    }

    /**
     * 로그인
     * [POST] "/login"
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PostLoginReq postLoginReq) {

        PostLoginRes postLoginRes = loginService.login(postLoginReq);
        return ResponseEntity.ok(postLoginRes);

    }
}
