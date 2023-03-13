package com.example.campick.model.dto;

import com.example.campick.model.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.security.cert.CertPathBuilder;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class BoardDto {

    private Long userIdx;
    private Long boardIdx;
    private String title;
    private String contents;
    private String category;
    private String nickName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Builder
    public BoardDto(BoardEntity boardEntity){
        this.userIdx = boardEntity.getUserEntity().getUserIdx();
        this.boardIdx = boardEntity.getBoardIdx();
        this.title = boardEntity.getTitle();
        this.contents = boardEntity.getContents();
        this.category = boardEntity.getCategory();
        this.nickName = boardEntity.getUserEntity().getNickname();
        this.createdAt = boardEntity.getCreatedAt();
        this.updatedAt = boardEntity.getUpdatedAt();
    }
}
