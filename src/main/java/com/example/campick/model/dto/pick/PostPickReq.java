package com.example.campick.model.dto.pick;

import com.example.campick.model.entity.PartyEntity;
import com.example.campick.model.entity.PickEntity;
import com.example.campick.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
public class PostPickReq {
    @NotNull(message = "회원의 인덱스를 입력해주세요.")
    private Long userIdx;
    @NotNull(message = "모임의 인덱스를 입력해주세요.")
    private Long partyIdx;

    public PickEntity toEntity(UserEntity userEntity, PartyEntity partyEntity) {
        return PickEntity.builder()
                .userEntity(userEntity)
                .partyEntity(partyEntity)
                .build();
    }
}
