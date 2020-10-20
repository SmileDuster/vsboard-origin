package com.smileduster.vsboard.api.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ResponseCode {

    success("0", "ok"),
    Insert
    ;

    private String code;

    private String msg;

    ResponseCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
