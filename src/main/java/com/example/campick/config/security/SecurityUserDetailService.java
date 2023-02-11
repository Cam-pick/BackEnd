package com.example.campick.config.security;

import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public SecurityUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUniqueId(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 사용자 없음"));
        return new SecurityUser(userEntity);
    }
}
