package com.example.campick.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "pick")
@DynamicInsert
public class PickEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pickIdx;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = PartyEntity.class)
    @JoinColumn(name = "partyIdx", updatable = false)
    private PartyEntity partyEntity;

    @Builder
    public PickEntity(UserEntity userEntity, PartyEntity partyEntity) {
        this.userEntity = userEntity;
        this.partyEntity = partyEntity;
    }
}
