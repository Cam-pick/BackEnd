package com.example.campick.service;

import com.example.campick.config.security.JwtTokenProvider;
import com.example.campick.model.dto.PostJoinReq;
import com.example.campick.model.dto.PostJoinRes;
import com.example.campick.model.dto.PostLoginReq;
import com.example.campick.model.dto.PostLoginRes;
import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static com.example.campick.model.entity.Role.ROLE_USER;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public PostJoinRes join(PostJoinReq postJoinReq) {
        UserEntity userEntity = loginRepository.save(UserEntity.builder()
                        .uniqueId(postJoinReq.getUniqueId())
                        .nickname(postJoinReq.getNickname())
                        .password(passwordEncoder.encode(postJoinReq.getPassword()))
                        .introduce(postJoinReq.getIntroduce())
                        .email(postJoinReq.getEmail())
                        .role(ROLE_USER)
                        .build());
        return new PostJoinRes(userEntity.getUserIdx());

    }

    /**
     * 로그인
     */
    @Transactional
    public PostLoginRes login(PostLoginReq postLoginReq) {
        UserEntity userEntity = loginRepository.findByUniqueId(postLoginReq.getUniqueId())
                .orElseThrow(() -> new IllegalArgumentException("가입 되지 않은 이메일입니다."));
        if (!passwordEncoder.matches(postLoginReq.getPassword(), userEntity.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 맞지 않습니다.");
        }

        String accessToken = jwtTokenProvider.createAccessToken(userEntity.getUniqueId(), userEntity.getRole());

        return new PostLoginRes(userEntity.getUserIdx(), accessToken);
    }

}
