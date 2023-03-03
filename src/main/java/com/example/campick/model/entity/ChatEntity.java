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
@Table(name = "chat")
@DynamicInsert
public class ChatEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyIdx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    // 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroomIdx")
    private ChatroomEntity chatroomEntity;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    @Builder
    public ChatEntity(String message, ChatroomEntity chatroomEntity, UserEntity userEntity) {
        this.message = message;
        this.chatroomEntity = chatroomEntity;
        this.userEntity = userEntity;
    }
}
