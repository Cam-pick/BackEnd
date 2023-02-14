package com.example.campick.controller;

import com.example.campick.model.dto.*;
import com.example.campick.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // JSON 형태 결과값을 반환해줌.
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. Autowired 역할
public class TestController {

    private final TestService testService;

    @GetMapping(value = "/health-check")
    public ResponseEntity<?> test(){
        TestDto testDto = testService.test();
        return ResponseEntity.ok(testDto);
    }

}