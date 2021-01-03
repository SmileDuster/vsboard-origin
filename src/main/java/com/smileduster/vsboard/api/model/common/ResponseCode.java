package com.smileduster.vsboard.api.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ResponseCode {

    success("0", "Done."),
    loginFail("f1000", "Login failed."),

    illegalGroup("f2001", "Illegal group provided."),
    illegalBattle("f2002", "Illegal battle provided."),

    unknownTarget("f3000", "Unknown target."),


    unknownError("e0000", "Unknown error occurred."),
    comingSoon("f0000", "Coming soon.");

    private String code;

    private String msg;

    ResponseCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
