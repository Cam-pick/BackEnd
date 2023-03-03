package com.example.campick.model.entity;

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
@Table(name = "chatroom")
@DynamicInsert
public class ChatroomEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatroomIdx;

    // 연관 관계
    @OneToMany(mappedBy = "chatroomEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatEntity> chatEntities = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "userIdx", name = "fromIdx")
    private UserEntity fromUserEntity;

    @ManyToOne( fetch = FetchType.LAZY, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "userIdx", name = "toIdx")
    private UserEntity toUserEntity;

    @Builder
    public ChatroomEntity(UserEntity fromUserEntity, UserEntity toUserEntity) {
        this.fromUserEntity = fromUserEntity;
        this.toUserEntity = toUserEntity;
    }
}
