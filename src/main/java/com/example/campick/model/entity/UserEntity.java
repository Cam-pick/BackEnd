package com.example.campick.model.entity;

import com.example.campick.model.BaseEntity;
import com.example.campick.model.Role;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Setter : 객체가 변경될 가능성이 있으므로 사용하지 않음, 영속성 유지를 위해 안씀
//@AllArgsConstructor : 모든 필드에 대해 생성자 자동 생성해줌, 객체 생성 시 인자의 순서 맞춰야 함 / new() 방식을 권장하지 않음 -> 빌더패턴 사용(인자의 순서와 상관없이 객체 생성 가능)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 의미없는 객체 생성을 방지하기 위함 / 빌더와 같이 사용할 수 없음 -> 생성자 메소드에 빌더 붙임
@Entity
@Table(name = "user")
@DynamicInsert
@ToString
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userIdx;

    @Column(length = 20, unique = true, nullable = false)
    private String uniqueId;

    @Column(length = 100, unique = true, nullable = false)
    private String password;

    @Column(length = 20, unique = true, nullable = false)
    private String nickname;

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column(length = 50)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private Role role;


    // 연관 관계
    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true) // FK가 없는 쪽에 mappedBy 사용을 추천
    private List<PartyEntity> partyEntities = new ArrayList<>(); // 엔티티에 Null이 있는 것은 좋지 않기 때문에 ArrayList 인스턴스를 넣어 Null을 방지합니다.

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ApplyEntity> applyEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PickEntity> pickEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoardEntity> boardEntities = new ArrayList<>();

    @OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> commentEntities = new ArrayList<>();

    @OneToMany(mappedBy = "fromUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatroomEntity> chatroomEntities1 = new ArrayList<>();

    @OneToMany(mappedBy = "toUserEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatroomEntity> chatroomEntities2 = new ArrayList<>();


    @Builder
    public UserEntity(Long userIdx, String uniqueId, String password, String nickname, String introduce, String email, String imageUrl, Role role) {
        this.userIdx = userIdx;
        this.uniqueId = uniqueId;
        this.password = password;
        this.nickname = nickname;
        this.introduce = introduce;
        this.email = email;
        this.imageUrl = imageUrl;
        this.role = role;
    }
}
