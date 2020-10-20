package com.smileduster.vsboard.api.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Response {

    private String code;

    private String msg;

    private String tips;

    private Object data;

    public static Response success(){
        return new Response(ResponseCode.success);
    }

    public static Response success(String tips){
        return new Response(ResponseCode.success, tips);
    }

    public static Response success(Object data){
        return new Response(ResponseCode.success, data);
    }

    public static Response fail(ResponseCode errorCode){
        return new Response(errorCode);
    }

    public static Response fail(ResponseCode errorCode, String tips){
        return new Response(errorCode, tips);
    }

    private Response(ResponseCode code) {
        this.code = code.getCode();
        this.msg = code.getMsg();
    }

    private Response(ResponseCode code, String tips) {
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.tips = tips;
    }

    private Response(ResponseCode code, Object data){
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

}
