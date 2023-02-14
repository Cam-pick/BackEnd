package com.example.campick.repository;

import com.example.campick.model.entity.PartyEntity;
import com.example.campick.model.entity.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

import static com.example.campick.model.Role.*;

@SpringBootTest
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PartyRepository partyRepository;

    @Test
    void testCreateUser() {
        // given
        UserEntity userEntity = UserEntity.builder()
                .email("test@email.com")
                .nickname("test")
                .password("test1234")
                .role(ROLE_USER)
                .uniqueId("testId")
                .build();
        PartyEntity partyEntity = PartyEntity.builder()
                .title("영어 공부 모임")
                .contents("영어 공부 모임 : 1시30분 / 강남역")
                .tag("#영어#공부")
                .capacity(4)
                .period("2023-02-14 ~ 2023-02-20")
                .build();

        partyEntity.setUserIdx(userEntity);

        // when
        UserEntity savedUser = userRepository.save(userEntity);
        PartyEntity savedParty = partyRepository.save(partyEntity);

        // then
        assertThat(savedUser.getEmail()).isEqualTo("test@email.com");
        assertThat(savedUser.getNickname()).isEqualTo("test");
        assertThat(savedUser.getPassword()).isEqualTo("test1234");
        assertThat(savedUser.getRole()).isEqualTo(ROLE_USER);
        assertThat(savedUser.getUniqueId()).isEqualTo("testId");

    }
}
