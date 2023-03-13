package com.example.campick.service;

import com.example.campick.error.PostErrorCode;
import com.example.campick.error.UserErrorCode;
import com.example.campick.exception.Post.PostNotFoundException;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.model.dto.BoardDto;
import com.example.campick.model.entity.BoardEntity;
import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.CommunityRepository;
import com.example.campick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityService {

    private final CommunityRepository communityRepository;
    private final UserRepository userRepository;

    public List<BoardDto> getAllPosts() {

        List<BoardEntity> boardEntity = communityRepository.findAllByOrderByCreatedAtDesc();

        return boardEntity.stream().map(BoardDto::new).collect(Collectors.toList());

    }

    public List<BoardDto> getAllPostsByPaging(Pageable pageable) {

        List<BoardEntity> boardEntity = communityRepository.findAllByOrderByCreatedAtDesc(pageable);

        return boardEntity.stream().map(BoardDto::new).collect(Collectors.toList());

    }

    public List<BoardDto> getPostsByCategory(String category, String search, Pageable pageable) {

        List<BoardEntity> boardEntity = communityRepository.findAllByCategoryAndTitleContainingOrderByCreatedAtDesc(category, search, pageable);

        return boardEntity.stream().map(BoardDto::new).collect(Collectors.toList());

    }

    public BoardDto getPost(Long id) {

        // 게시글 존재여부 판단
        BoardEntity boardEntity = communityRepository.findById(id).orElseThrow(() -> new PostNotFoundException(
                "Post not present in the database", PostErrorCode.POST_NOT_FOUND_EXCEPTION));

        // Entity to DTO
        BoardDto boardDto = BoardDto.builder()
                .userIdx(boardEntity.getUserEntity().getUserIdx())
                .boardIdx(boardEntity.getBoardIdx())
                .title(boardEntity.getTitle())
                .contents(boardEntity.getContents())
                .category(boardEntity.getCategory())
                .nickName(boardEntity.getUserEntity().getNickname())
                .createdAt(boardEntity.getCreatedAt())
                .updatedAt(boardEntity.getUpdatedAt())
                .build();

        return boardDto;

    }

    public void createPost(BoardDto boardDto) {

        // TODO : 사용자 존재여부 판단
        UserEntity userEntity = userRepository.findById(boardDto.getUserIdx()).orElseThrow(() -> new UserNotFoundException(
                "User not present in the database", UserErrorCode.USER_NOT_FOUND_EXCEPTION));

        // DTO TO Entity => Save
        communityRepository.save(BoardEntity.builder()
            .title(boardDto.getTitle())
            .contents(boardDto.getContents())
            .category(boardDto.getCategory())
            .userEntity(userEntity)
            .build()
        );
    }
}
