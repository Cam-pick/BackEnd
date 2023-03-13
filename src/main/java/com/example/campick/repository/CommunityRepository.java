package com.example.campick.repository;

import com.example.campick.model.entity.BoardEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<BoardEntity, Long> {

    List<BoardEntity> findAllByOrderByCreatedAtDesc();

    List<BoardEntity> findAllByOrderByCreatedAtDesc(Pageable pageable);

    List<BoardEntity> findAllByCategoryOrderByCreatedAtDesc(String category, Pageable pageable);

    List<BoardEntity> findAllByCategoryAndTitleContainingOrderByCreatedAtDesc(String category, String search, Pageable pageable);
}
