package com.example.campick.model.dto.party;

import com.example.campick.model.entity.PartyEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class GetPartyDetailsRes {
    private Long partyIdx;
    private String imageUrl;
    private String title;
    private String tag;
    private String contents;
    private Long userIdx;

    public GetPartyDetailsRes(PartyEntity partyEntity) {
        partyIdx = partyEntity.getPartyIdx();
        imageUrl = partyEntity.getImageUrl();
        title = partyEntity.getTitle();
        tag = partyEntity.getTag();
        contents = partyEntity.getContents();
        userIdx = partyEntity.getUserEntity().getUserIdx();
    }
}
