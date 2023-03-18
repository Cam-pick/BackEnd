package com.example.campick.repository;

import com.example.campick.model.entity.ApplyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplyRepository extends JpaRepository<ApplyEntity, Long> {

}
