package com.example.campick.model.entity;

import com.example.campick.model.BaseEntity;
import lombok.AccessLevel;
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
    @OneToMany(mappedBy = "chatroomEntity", cascade = CascadeType.MERGE, orphanRemoval = true)
    private List<ChatEntity> chatEntities = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "userIdx", name = "fromIdx", updatable = false)
    private UserEntity fromUserEntity;

    public void setFromIdx(UserEntity userEntity) {
        this.fromUserEntity = userEntity;
    }

    @ManyToOne(cascade = CascadeType.MERGE, targetEntity = UserEntity.class)
    @JoinColumn(referencedColumnName = "userIdx", name = "toIdx", updatable = false)
    private UserEntity toUserEntity;

    public void setToIdx(UserEntity userEntity) {
        this.toUserEntity = userEntity;
    }

}
