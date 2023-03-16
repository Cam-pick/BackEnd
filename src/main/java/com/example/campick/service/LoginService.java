package com.example.campick.service;

import com.example.campick.error.UserErrorCode;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.jwt.JwtTokenProvider;
import com.example.campick.model.dto.JoinDto;
import com.example.campick.model.dto.LoginReqDto;
import com.example.campick.model.dto.LoginResDto;
import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.campick.model.entity.Role.ROLE_USER;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class LoginService {
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     */
    @Transactional
    public void join(JoinDto joinDto) {

        userRepository.save(UserEntity.builder()
                .uniqueId(joinDto.getUniqueId())
                .nickname(joinDto.getNickname())
                .password(passwordEncoder.encode(joinDto.getPassword()))
                .introduce(joinDto.getIntroduce())
                .email(joinDto.getEmail())
                .role(ROLE_USER)
                .build());

    }

    /**
     * 로그인
     */
    @Transactional
    public LoginResDto login(LoginReqDto loginReqDto) {

        UserEntity userEntity = userRepository.findByUniqueIdAndStatus(loginReqDto.getUniqueId(), "A")
                .orElseThrow(() -> new UserNotFoundException(
                        "Invalid UserId", UserErrorCode.INVALID_USER_ID));

        if (!passwordEncoder.matches(loginReqDto.getPassword(), userEntity.getPassword())) {
            throw new UserNotFoundException("Invalid User Password", UserErrorCode.INVALID_USER_PWD);
        }

        String accessToken = jwtTokenProvider.createAccessToken(userEntity.getUserIdx(), userEntity.getRole());

        return new LoginResDto(userEntity.getUserIdx(), accessToken);

    }

    /**
     * 회원 탈퇴
     */
    @Transactional
    public void resign(Long userIdx) {

        UserEntity userEntity = userRepository.findByUserIdxAndStatus(userIdx, "A")
                .orElseThrow(() -> new UserNotFoundException(
                        "Invalid User Index", UserErrorCode.USER_INDEX_INVALID_EXCEPTION));

        userEntity.setStatus("D");

        userRepository.save(userEntity);

    }
}
