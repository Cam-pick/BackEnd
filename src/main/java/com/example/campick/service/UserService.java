package com.example.campick.service;

import com.example.campick.config.security.JwtTokenProvider;
import com.example.campick.model.dto.UserDto;
import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.campick.model.Role.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Long createUser(UserDto userDto) {
        return userRepository.save(UserEntity.builder()
                        .uniqueId(userDto.getUniqueId())
                        .password(passwordEncoder.encode(userDto.getPassword()))
                        .role(ROLE_USER)
                        .nickname(userDto.getNickname())
                        .email(userDto.getEmail())
                        .imageUrl(userDto.getImageUrl())
                        .introduce(userDto.getIntroduce())
                        .build()).getUserIdx();
    }

    public String login(UserDto userDto) {
        // 해당 아이디을 가진 회원이 있는지 확인합니다.
        UserEntity userEntity = userRepository.findByUniqueId(userDto.getUniqueId())
                .orElseThrow(() -> new IllegalArgumentException("없는 아이디입니다."));
        System.out.println(userEntity);
        // 입력받은 비번과 회원의 비번과 일치한지 확인합니다.
        if(!passwordEncoder.matches(userDto.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // 해당 아이디의 회원이 존재하고 비밀번호도 일치한다면, JWT 를 생성합니다.
        return jwtTokenProvider.createToken(userEntity.getUniqueId(), userEntity.getRole());
    }

}
