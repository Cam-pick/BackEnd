package com.example.campick.repository;

import com.example.campick.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByUniqueId(String uniqueId);

}
