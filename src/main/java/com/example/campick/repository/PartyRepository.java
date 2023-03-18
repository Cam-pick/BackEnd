package com.example.campick.repository;

import com.example.campick.model.entity.PartyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<PartyEntity, Long> {
    Optional<List<PartyEntity>> findByStatus(String status);
    Optional<PartyEntity> findByPartyIdxAndStatus(Long partyIdx, String status);
    Page<PartyEntity> findByTitleContainingAndTagContaining(String keyword, String tag, Pageable pageable);
    Page<PartyEntity> findByTagContaining(String tag, Pageable pageable);
    Page<PartyEntity> findByTitleContaining(String keyword, Pageable pageable);
}
