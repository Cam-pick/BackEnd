package com.example.campick.model.entity;

import com.example.campick.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "party")
@DynamicInsert
public class PartyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partyIdx;

    @Column(length = 20, unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Column(length = 100, nullable = false)
    private String tag;

    // 연관 관계
    @OneToMany(mappedBy = "partyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplyEntity> applyEntities = new ArrayList<>();

    @OneToMany(mappedBy = "partyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PickEntity> pickEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    // 연관관계 설정
    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Builder
    public PartyEntity(Long partyIdx, String title, String contents, String imageUrl, String tag) {
        this.partyIdx = partyIdx;
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
    }

}
