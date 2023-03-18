package com.example.campick.exception.Party;

import com.example.campick.error.PartyErrorCode;
import com.example.campick.error.PostErrorCode;
import lombok.Getter;

@Getter
public class PartyNotFoundException extends RuntimeException{

    private PartyErrorCode partyErrorCode;

    public PartyNotFoundException(String msg, PartyErrorCode partyErrorCode){
        super(msg);
        this.partyErrorCode = partyErrorCode;
    }

}
