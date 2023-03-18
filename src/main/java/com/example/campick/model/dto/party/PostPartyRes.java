package com.example.campick.model.dto.party;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostPartyRes {
    private Long partyIdx;
    private String message;
}
