package com.example.campick.controller;

import com.example.campick.model.dto.*;
import com.example.campick.service.TestService;
import com.example.campick.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태 결과값을 반환해줌.
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. Autowired 역할
public class TestController {

    private final TestService testService;
    private final UserService userService;

    @GetMapping(value = "/health-check")
    public ResponseEntity<?> test(){
        TestDto testDto = testService.test();
        return ResponseEntity.ok(testDto);
    }

    /**
     * 회원가입
     * @param userDto
     * @return
     */
    @PostMapping(value = "/signup")
    @ResponseBody
    public ResponseEntity<?> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUser(userDto));
    }

    /**
     * 로그인
     * @param userDto
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.login(userDto));
    }
}