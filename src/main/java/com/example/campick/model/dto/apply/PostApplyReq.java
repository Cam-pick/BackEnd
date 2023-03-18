package com.example.campick.model.dto.apply;

import com.example.campick.model.entity.ApplyEntity;
import com.example.campick.model.entity.PartyEntity;
import com.example.campick.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
public class PostApplyReq {
    @NotNull(message = "신청자의 인덱스를 입력해주세요.")
    private Long userIdx;
    @NotNull(message = "신청 대상 모임의 인덱스를 입력해주세요.")
    private Long partyIdx;

    public ApplyEntity toEntity(UserEntity userEntity, PartyEntity partyEntity) {
        return ApplyEntity.builder()
                .userEntity(userEntity)
                .partyEntity(partyEntity)
                .build();
    }
}
