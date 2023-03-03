package com.example.campick.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class PostLoginReq {
    private String uniqueId;
    private String password;
}
