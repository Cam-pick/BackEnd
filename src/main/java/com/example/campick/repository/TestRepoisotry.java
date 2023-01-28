package com.example.campick.repository;

import com.example.campick.model.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestRepoisotry extends JpaRepository<TestEntity, Long> {

}
