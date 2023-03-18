package com.example.campick.service;

import com.example.campick.error.PartyErrorCode;
import com.example.campick.error.UserErrorCode;
import com.example.campick.exception.Party.PartyNotFoundException;
import com.example.campick.exception.User.UserNotFoundException;
import com.example.campick.model.dto.apply.PostApplyReq;
import com.example.campick.model.dto.apply.PostApplyRes;
import com.example.campick.model.dto.party.*;
import com.example.campick.model.dto.pick.PostPickReq;
import com.example.campick.model.dto.pick.PostPickRes;
import com.example.campick.model.entity.ApplyEntity;
import com.example.campick.model.entity.PartyEntity;
import com.example.campick.model.entity.PickEntity;
import com.example.campick.model.entity.UserEntity;
import com.example.campick.repository.ApplyRepository;
import com.example.campick.repository.PartyRepository;
import com.example.campick.repository.PickRepository;
import com.example.campick.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PartyService {

    @Value("${file.dir}")
    private String fileDir;

    private final UserRepository userRepository;
    private final PartyRepository partyRepository;
    private final ApplyRepository applyRepository;
    private final PickRepository pickRepository;

    /**
     * 모든 모임 조회
     */
    @Transactional
    public List<GetPartyRes> getParties() {
        List<PartyEntity> partyEntityList = partyRepository.findByStatus("A")
                .orElseThrow(() -> new NoSuchElementException());
        return partyEntityList.stream().map(GetPartyRes::new).collect(Collectors.toList());
    }

    /**
     * 특정 모임의 세부정보 조회
     */
    @Transactional
    public GetPartyDetailsRes getParty(Long partyIdx) {
        PartyEntity partyEntity = partyRepository.findByPartyIdxAndStatus(partyIdx, "A")
                .orElseThrow(() -> new PartyNotFoundException(
                        "Party Not Found In DB or Invalid Index", PartyErrorCode.PARTY_INDEX_INVALID_EXCEPTION));
        return new GetPartyDetailsRes(partyEntity);
    }

    /**
     * 모임 생성
     */
    @Transactional
    public Long createParty(PostPartyReq postPartyReq, MultipartFile files) throws IOException {

        if (files.isEmpty()) {
            throw new PartyNotFoundException("Null Image File", PartyErrorCode.NULL_IMAGE_FILE);
        }

        // 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        UserEntity userEntity = userRepository.findByUserIdxAndStatus(postPartyReq.getUserIdx(), "A")
                .orElseThrow(() -> new UserNotFoundException(
                        "User Not Found In DB or Invalid Index", UserErrorCode.USER_INDEX_INVALID_EXCEPTION));

        PartyEntity partyEntity = postPartyReq.toEntity(userEntity);

        partyEntity.setImage(savedPath);

        // 실제로 로컬에 uuid를 파일명으로 저장
        files.transferTo(new File(savedPath));

        Long partyIdx = partyRepository.save(partyEntity).getPartyIdx();

        return partyIdx;
    }

    /**
     * 모임 신청
     */
    @Transactional
    public PostApplyRes joinParty(PostApplyReq postApplyReq) {
        UserEntity userEntity = userRepository.findById(postApplyReq.getUserIdx())
                .orElseThrow(() -> new UserNotFoundException(
                        "User Not Found In DB or Invalid Index", UserErrorCode.USER_INDEX_INVALID_EXCEPTION));
        PartyEntity partyEntity = partyRepository.findById(postApplyReq.getPartyIdx())
                .orElseThrow(() -> new PartyNotFoundException(
                        "Party Not Found In DB or Invalid Index", PartyErrorCode.PARTY_INDEX_INVALID_EXCEPTION));

        ApplyEntity applyEntity = postApplyReq.toEntity(userEntity, partyEntity);

        Long applyIdx = applyRepository.save(applyEntity).getApplyIdx();
        return new PostApplyRes(applyIdx);
    }

    /**
     * 모임 좋아요 (♥)
     */
    @Transactional
    public PostPickRes pickParty(PostPickReq postPickReq) {
        UserEntity userEntity = userRepository.findByUserIdxAndStatus(postPickReq.getUserIdx(), "A")
                .orElseThrow(() -> new UserNotFoundException(
                        "User Not Found In DB or Invalid Index", UserErrorCode.USER_INDEX_INVALID_EXCEPTION));
        PartyEntity partyEntity = partyRepository.findByPartyIdxAndStatus(postPickReq.getPartyIdx(), "A")
                .orElseThrow(() -> new PartyNotFoundException(
                        "Party Not Found In DB or Invalid Index", PartyErrorCode.PARTY_INDEX_INVALID_EXCEPTION));

        PickEntity pickEntity = postPickReq.toEntity(userEntity, partyEntity);

        Long pickIdx = pickRepository.save(pickEntity).getPickIdx();
        return new PostPickRes(pickIdx);
    }

    /**
     * 모임 글 수정
     */
    @Transactional
    public void modifyParty(Long partyIdx, PatchPartyReq patchPartyReq, MultipartFile files) throws IOException{
        PartyEntity partyEntity = partyRepository.findByPartyIdxAndStatus(partyIdx, "A")
                .orElseThrow(() -> new PartyNotFoundException(
                        "Party Not Found In DB or Invalid Index", PartyErrorCode.PARTY_INDEX_INVALID_EXCEPTION));

        if (files.isEmpty()) {
            throw new PartyNotFoundException("Null Image File", PartyErrorCode.NULL_IMAGE_FILE);
        }

        // 원래 파일 이름 추출
        String origName = files.getOriginalFilename();

        // 파일 이름으로 쓸 uuid 생성
        String uuid = UUID.randomUUID().toString();

        // 확장자 추출(ex : .png)
        String extension = origName.substring(origName.lastIndexOf("."));

        // uuid와 확장자 결합
        String savedName = uuid + extension;

        // 파일을 불러올 때 사용할 파일 경로
        String savedPath = fileDir + savedName;

        partyEntity.update(patchPartyReq.getTitle(), patchPartyReq.getContents(), savedPath,
                patchPartyReq.getTag(), patchPartyReq.getCapacity(), patchPartyReq.getStartDate(), patchPartyReq.getDueDate());

        partyRepository.save(partyEntity);
    }

    /**
     * 모임 글 삭제
     */
    @Transactional
    public void deleteParty(Long partyIdx) {
        PartyEntity partyEntity = partyRepository.findByPartyIdxAndStatus(partyIdx, "A")
                .orElseThrow(() -> new PartyNotFoundException(
                        "Party Not Found In DB or Invalid Index", PartyErrorCode.PARTY_INDEX_INVALID_EXCEPTION));

        partyEntity.setStatus("D");

        partyRepository.save(partyEntity);
    }

    /**
     * 모임 글 검색, 정렬, 페이징, 태그
     */
    @Transactional
    public List<GetPartyRes> search(String keyword, String tag, Pageable pageable) throws Exception {
        try {
            if ((keyword.isEmpty() || keyword == null) && (tag.isEmpty() || tag == null)) {

                Page<PartyEntity> partyEntities = partyRepository.findAll(pageable);
                return partyEntities.stream().map(GetPartyRes::new).collect(Collectors.toList());

            } else if (keyword.isEmpty() || keyword == null) {

                Page<PartyEntity> partyEntities = partyRepository.findByTagContaining(tag, pageable);
                return partyEntities.stream().map(GetPartyRes::new).collect(Collectors.toList());

            } else if (tag.isEmpty() || tag == null) {

                Page<PartyEntity> partyEntities = partyRepository.findByTitleContaining(keyword, pageable);
                return partyEntities.stream().map(GetPartyRes::new).collect(Collectors.toList());

            }

            Page<PartyEntity> partyEntities = partyRepository.findByTitleContainingAndTagContaining(keyword, tag, pageable);
            return partyEntities.stream().map(GetPartyRes::new).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

}
