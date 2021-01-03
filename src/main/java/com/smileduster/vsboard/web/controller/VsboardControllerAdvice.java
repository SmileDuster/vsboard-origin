package com.smileduster.vsboard.web.controller;

import com.smileduster.vsboard.api.model.common.Response;
import com.smileduster.vsboard.api.model.common.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Slf4j
public class VsboardControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Response<?> handleGlobal(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Response.create(ResponseCode.unknownError, "未知错误");
    }

}
