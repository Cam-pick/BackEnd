package com.example.campick.handler;

import com.example.campick.exception.Party.PartyNotFoundException;
import com.example.campick.model.dto.error.PartyErrorResponse;
import com.example.campick.model.dto.error.PostErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class PartyExceptionHandler {

    @ExceptionHandler(PartyNotFoundException.class)
    public ResponseEntity<PartyErrorResponse> postNotFoundException(PartyNotFoundException e){
        log.error("partyNotFoundException",e);
        PartyErrorResponse response = new PartyErrorResponse(e.getPartyErrorCode());
        return new ResponseEntity<>(response, HttpStatus.valueOf(e.getPartyErrorCode().getStatus()));
    }

}
