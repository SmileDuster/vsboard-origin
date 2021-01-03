package com.smileduster.vsboard.api.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public final class Response<T> {

    private String code;

    private String msg;

    private String tips;

    private T data;

    public static Response todo() {
        return new Response<>(ResponseCode.comingSoon);
    }

    public static Response<?> create(){
        return new Response<>(ResponseCode.success);
    }

    public static Response<?> create(String tips){
        return new Response<>(ResponseCode.success, tips);
    }

    public static <T> Response<T> create(ResponseCode code, T data) {
        return new Response<>(code, data);
    }

    public static <T> Response<T> create(T data){
        return new Response<>(ResponseCode.success, data);
    }

    public static Response<?> create(ResponseCode code){
        return new Response<>(code);
    }

    public static Response<?> create(ResponseCode code, String tips){
        return new Response<>(code, tips);
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

    private Response(ResponseCode code, T data){
        this.code = code.getCode();
        this.msg = code.getMsg();
        this.data = data;
    }

}
