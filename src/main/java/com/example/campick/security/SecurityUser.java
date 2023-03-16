package com.example.campick.security;

import com.example.campick.model.entity.UserEntity;
import org.springframework.security.core.authority.AuthorityUtils;

public class SecurityUser extends org.springframework.security.core.userdetails.User {

    private UserEntity userEntity;

    public SecurityUser(UserEntity userEntity) {
        super(userEntity.getUniqueId(), userEntity.getPassword(), AuthorityUtils.createAuthorityList(userEntity.getRole().toString()));
        this.userEntity = userEntity;
    }

    public UserEntity getMember() {
        return userEntity;
    }
}