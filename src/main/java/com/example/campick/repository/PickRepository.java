package com.example.campick.repository;

import com.example.campick.model.entity.PickEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PickRepository extends JpaRepository<PickEntity, Long> {

}
