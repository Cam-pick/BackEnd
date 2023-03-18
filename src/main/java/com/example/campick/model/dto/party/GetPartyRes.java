package com.example.campick.model.dto.party;

import com.example.campick.model.entity.PartyEntity;
import lombok.*;

import java.sql.Timestamp;


@Builder
@Data
@AllArgsConstructor
public class GetPartyRes {
    private Long partyIdx;
    private String imageUrl;
    private String title;
    private Integer capacity;
    private Timestamp startDate;
    private Timestamp dueDate;
    private Long userIdx;

    public GetPartyRes(PartyEntity partyEntity) {
        partyIdx = partyEntity.getPartyIdx();
        imageUrl = partyEntity.getImageUrl();
        title = partyEntity.getTitle();
        capacity = partyEntity.getCapacity();
        startDate = partyEntity.getStartDate();
        dueDate = partyEntity.getDueDate();
        userIdx = partyEntity.getUserEntity().getUserIdx();
    }

}
