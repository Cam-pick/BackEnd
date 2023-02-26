package com.example.campick.model.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commentIdx", updatable = false)
    private CommentEntity commentEntity;

    @Builder
    public ReplyEntity(String comment, UserEntity userEntity, CommentEntity commentEntity) {
        this.comment = comment;
        this.userEntity = userEntity;
        this.commentEntity = commentEntity;
    }
}
