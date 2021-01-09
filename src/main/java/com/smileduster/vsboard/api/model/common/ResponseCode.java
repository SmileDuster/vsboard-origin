package com.smileduster.vsboard.api.model.common;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public enum ResponseCode {

    success("0", "Done."),
    loginFail("f1000", "Login failed."),
    dupEmail("f1100", "Provided email is registered."),

    unknownTarget("f2000", "Unknown target."),

    illegalGroup("f2101", "Illegal group provided."),
    illegalBattle("f2102", "Illegal battle provided."),
    illegalMember("f2103", "Illegal member provided."),
    dependentBattle("f2200", "Some battles are relying on the target that will be deleted."),


    unknownError("e0000", "Unknown error occurred."),
    comingSoon("f0000", "Coming soon.");

    private String code;

    private String msg;

    ResponseCode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

}
