package com.example.campick.controller;

import com.example.campick.error.PartyErrorCode;
import com.example.campick.exception.Party.PartyNotFoundException;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.model.dto.apply.PostApplyReq;
import com.example.campick.model.dto.apply.PostApplyRes;
import com.example.campick.model.dto.party.*;
import com.example.campick.model.dto.pick.PostPickReq;
import com.example.campick.model.dto.pick.PostPickRes;
import com.example.campick.repository.PartyRepository;
import com.example.campick.repository.UserRepository;
import com.example.campick.service.PartyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parties")
public class PartyController {

    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final PartyService partyService;

    /**
     * 모든 모임 조회 API
     * [GET] /parties
     */
    @ResponseBody
    @GetMapping("")
    public ResponseEntity<?> getParties() {
        List<GetPartyRes> getPartyResList = partyService.getParties();
        return ResponseEntity.ok(getPartyResList);
    }

    /**
     * 특정 모임의 세부정보 조회 API
     * [GET] /parties/:partyIdx
     */
    @ResponseBody
    @GetMapping("/{partyIdx}")
    public ResponseEntity<?> getParty(@PathVariable("partyIdx") Long partyIdx) {
        GetPartyDetailsRes getPartyDetailsRes = partyService.getParty(partyIdx);
        return ResponseEntity.ok(getPartyDetailsRes);
    }

    /**
     * 모임 생성
     * [POST] /parties/new
     */
    @ResponseBody
    @PostMapping("/new")
    public ResponseEntity<?> createParty(@Valid PostPartyReq postPartyReq,
                                         MultipartFile file, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            throw new PartyNotFoundException("Input regex error or null", PartyErrorCode.ILLEGAL_INPUT);
        }
        Long partyIdx = partyService.createParty(postPartyReq, file);
        PostPartyRes postPartyRes = new PostPartyRes(partyIdx, "생성에 성공했습니다.");
        return ResponseEntity.ok(postPartyRes);
    }

    /**
     * 모임 신청
     * [POST] /parties/applications
     */
    @ResponseBody
    @PostMapping("/applications")
    public ResponseEntity<?> joinParty(@RequestBody @Valid PostApplyReq postApplyReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new PartyNotFoundException("Input regex error or null", PartyErrorCode.ILLEGAL_INPUT);
        }

        PostApplyRes postApplyRes = partyService.joinParty(postApplyReq);
        return ResponseEntity.ok(postApplyRes);
    }

    /**
     * 모임 좋아요 (♥)
     * [POST] /parties/favorites
     */
    @ResponseBody
    @PostMapping("/favorites")
    public ResponseEntity<?> pickParty(@RequestBody @Valid PostPickReq postPickReq, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }

        PostPickRes postPickRes = partyService.pickParty(postPickReq);
        return ResponseEntity.ok(postPickRes);
    }

    /**
     * 모임 글 수정
     * [PATCH] /parties/:partyIdx
     */
    @ResponseBody
    @PatchMapping("/{partyIdx}")
    public ResponseEntity<?> modifyParty(@PathVariable("partyIdx") Long partyIdx,
                                         @Valid PatchPartyReq patchPartyReq,
                                         MultipartFile file, BindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(bindingResult.getAllErrors());
        }
        partyService.modifyParty(partyIdx, patchPartyReq, file);
        return ResponseEntity.ok("수정 성공");
    }

    /**
     * 모임 글 삭제
     * [Patch] /parties/:partyIdx/d
     */
    @ResponseBody
    @PatchMapping("/{partyIdx}/d")
    public ResponseEntity<?> deleteParty(@PathVariable("partyIdx") Long partyIdx) {
        partyService.deleteParty(partyIdx);
        return ResponseEntity.ok("삭제 완료");
    }

    /**
     * 모임 검색 및 정렬 조회 (페이징)
     * [GET] /parties/search?page={page}&sort={sort}&tag={tag}&keyword={keyword}
     */
    @ResponseBody
    @GetMapping("/search")
    public ResponseEntity<?> searchParties(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "sort", required = false) String sort,
                                           @RequestParam(value = "tag", required = false) String tag, @RequestParam(value = "keyword", required = false) String keyword) throws Exception {
        System.out.println(page + sort + tag + keyword);
        if (page == null) {
            page = 0;
        }

        if(sort == null || sort.isEmpty()) {
            sort = "updatedAt";
        } else if (sort.equals("최신순")) {
            sort = "createAt";
        }
        System.out.println(page + sort + tag + keyword);

        Pageable pageable = PageRequest.of(page, 9, Sort.by(sort).ascending());

        List<GetPartyRes> getPartyResList = partyService.search(keyword, tag, pageable);
        return ResponseEntity.ok(getPartyResList);
    }
}
