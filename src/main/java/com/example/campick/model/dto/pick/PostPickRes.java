package com.example.campick.model.dto.pick;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostPickRes {
    private Long pickIdx;
}
