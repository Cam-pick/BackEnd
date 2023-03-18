package com.example.campick.model.dto.party;

import com.example.campick.model.entity.PartyEntity;
import com.example.campick.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class PatchPartyReq {

    @Size(min = 2, max = 50, message = "2 ~ 50자로 입력 가능합니다.")
    private String title;

    private String tag;

    private String contents;
    private Timestamp startDate;
    private Timestamp dueDate;
    private Integer capacity;
    private String imageUrl;

    @NotNull(message = "작성자의 인덱스를 입력해주세요.")
    private Long userIdx;

    public PartyEntity toEntity(UserEntity userEntity) {
        return PartyEntity.builder()
                .title(title)
                .tag(tag)
                .contents(contents)
                .startDate(startDate)
                .dueDate(dueDate)
                .capacity(capacity)
                .imageUrl(imageUrl)
                .userEntity(userEntity)
                .build();
    }
}
