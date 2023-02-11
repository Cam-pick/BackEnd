package com.example.campick.model.entity;

import com.example.campick.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "apply")
@DynamicInsert
public class ApplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyIdx;

    // 연관 관계
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = PartyEntity.class)
    @JoinColumn(name = "partyIdx", updatable = false)
    private PartyEntity partyEntity;

    public void setPartyIdx(PartyEntity partyEntity) {
        this.partyEntity = partyEntity;
    }

    @Builder
    public ApplyEntity(Long applyIdx) {
        this.applyIdx = applyIdx;
    }
}
