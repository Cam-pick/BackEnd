package com.example.campick.service;

import com.example.campick.model.dto.TestDto;
import com.example.campick.model.entity.TestEntity;
import com.example.campick.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final TestRepository testRepository;

    public TestDto test(){
        Optional<TestEntity> test = testRepository.findById(1L);

        TestDto testDto = TestDto.builder()
                .testId(test.get().getTestId())
                .build();

        return testDto;
    }
}