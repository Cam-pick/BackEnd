package com.example.campick.model.entity;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ToString
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

    @Column(length = 100)
    private String tag;

    @Column
    private Integer capacity;

    @Column
    private Timestamp startDate;

    @Column
    private Timestamp dueDate;


    // 연관 관계
    @OneToMany(mappedBy = "partyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplyEntity> applyEntities = new ArrayList<>();

    @OneToMany(mappedBy = "partyEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PickEntity> pickEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "userIdx")
    private UserEntity userEntity;

    @Builder
    public PartyEntity(String title, String contents, String imageUrl,
                       String tag, Integer capacity, Timestamp startDate, Timestamp dueDate, UserEntity userEntity) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.capacity = capacity;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.userEntity = userEntity;
    }

    public void update(String title, String contents, String imageUrl,
                       String tag, Integer capacity, Timestamp startDate, Timestamp dueDate) {
        this.title = title;
        this.contents = contents;
        this.imageUrl = imageUrl;
        this.tag = tag;
        this.capacity = capacity;
        this.startDate = startDate;
        this.dueDate = dueDate;
    }

    public void setImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
