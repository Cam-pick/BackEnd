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
@Table(name = "board")
@DynamicInsert
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardIdx;

    @Column(length = 20, unique = true, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String contents;

    @Column(length = 20, unique = true, nullable = false)
    private String category;

    // 연관 관계
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Builder
    public BoardEntity(Long boardIdx, String title, String contents, String category) {
        this.boardIdx = boardIdx;
        this.title = title;
        this.contents = contents;
        this.category = category;
    }
}
