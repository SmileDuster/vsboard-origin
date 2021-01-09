package com.smileduster.vsboard.web.controller;

import com.smileduster.vsboard.api.exception.VsboardServiceException;
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

    @ExceptionHandler(VsboardServiceException.class)
    public Response<?> handleServiceExc(VsboardServiceException e) {
        return Response.create(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Response<?> handleUnexpectedExc(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Response.create(ResponseCode.unknownError, e.getClass().getName());
    }

}
