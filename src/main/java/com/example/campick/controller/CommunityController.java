package com.example.campick.controller;

import com.example.campick.model.dto.BoardDto;
import com.example.campick.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태 결과값을 반환해줌.
@RequiredArgsConstructor // final 객체를 Constructor Injection 해줌. Autowired 역할
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping(value = "/boards")
    public ResponseEntity<?> getAllPosts(){

        List<BoardDto> boardDtos = communityService.getAllPosts();

        return ResponseEntity.ok(boardDtos);

    }

    @GetMapping(value = "/boards/paging")
    public ResponseEntity<?> getAllPostsByPaging(Pageable pageable){

        List<BoardDto> boardDtos = communityService.getAllPostsByPaging(pageable);

        return ResponseEntity.ok(boardDtos);

    }

    @PostMapping(value = "/boards/search")
    public ResponseEntity<?> getAllPostsByPaging(@RequestBody String category, @RequestBody String search, @RequestBody Pageable pageable){

        System.out.println(search);
        List<BoardDto> boardDtos = communityService.getPostsByCategory(category, search, pageable);

        return ResponseEntity.ok(boardDtos);

    }

    @GetMapping(value = "/boards/{id}")
    public ResponseEntity<?> getPost(@PathVariable Long id){

        BoardDto boardDtos = communityService.getPost(id);

        return ResponseEntity.ok(boardDtos);

    }

    @PostMapping(value = "/boards")
    public ResponseEntity<?> createPost(@RequestBody BoardDto boardDto){

        communityService.createPost(boardDto);

        return ResponseEntity.ok().build();

    }
}
