package com.example.campick.model.entity;

import com.example.campick.model.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "reply")
@DynamicInsert
public class ReplyEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyIdx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comment;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = CommentEntity.class)
    @JoinColumn(name = "commentIdx", updatable = false)
    private CommentEntity commentEntity;

    public void setCommentIdx(CommentEntity commentEntity) {
        this.commentEntity = commentEntity;
    }
}
