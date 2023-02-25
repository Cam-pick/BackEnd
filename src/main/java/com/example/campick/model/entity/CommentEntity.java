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
@Table(name = "comment")
@DynamicInsert
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentIdx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    // 연관 관계
    @OneToMany(mappedBy = "commentEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReplyEntity> replyEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = BoardEntity.class)
    @JoinColumn(name = "boardIdx", updatable = false)
    private BoardEntity boardEntity;

    public void setBoardIdx(BoardEntity boardEntity) {
        this.boardEntity = boardEntity;
    }

    @Builder
    public CommentEntity(Long commentIdx, String comment) {
        this.commentIdx = commentIdx;
        this.comment = comment;
    }
}
