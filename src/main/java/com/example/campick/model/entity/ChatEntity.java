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
@Table(name = "chat")
@DynamicInsert
public class ChatEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyIdx;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String message;

    // 연관 관계
    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = ChatroomEntity.class)
    @JoinColumn(name = "chatroomIdx", updatable = false)
    private ChatroomEntity chatroomEntity;

    public void setChatroomIdx(ChatroomEntity chatroomEntity) {
        this.chatroomEntity = chatroomEntity;
    }

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(name = "userIdx", updatable = false)
    private UserEntity userEntity;

    public void setUserIdx(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
