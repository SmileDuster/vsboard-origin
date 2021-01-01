package com.smileduster.vsboard.api.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ResponseCode {

    success("0", "done"),
    loginFail("f1000", "login failed"),


    unknown("e0000", "unknown error occurred"),
    comingSoon("f0000", "coming soon");

    private String code;

    private String msg;

    ResponseCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
