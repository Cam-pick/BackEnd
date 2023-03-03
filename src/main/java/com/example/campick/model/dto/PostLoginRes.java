package com.example.campick.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostLoginRes {
    private Long userIdx;
    private String accessToken;
}
