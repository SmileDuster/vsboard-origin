package com.smileduster.vsboard.web.core.controller;

import com.lxc.vsboard.api.common.ResponseCode;
import com.lxc.vsboard.api.common.WebResponse;
import com.lxc.vsboard.api.exception.ServiceFailException;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@CommonsLog
@RestControllerAdvice(basePackages = "com.lxc.vsboard.web.client.controller")
public class ResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        return !methodParameter.getParameterType().equals(WebResponse.class);
    }

    @Override
    public Object beforeBodyWrite(Object response,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (response instanceof ResponseCode){
            return WebResponse.createSuccess((ResponseCode) response);
        } else {
            return WebResponse.createGetData(response);
        }
    }

    @ExceptionHandler(ServiceFailException.class)
    public WebResponse<String> serviceFail(ServiceFailException e){
        log.warn(e.getCode());
        return WebResponse.createFail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public WebResponse<String> badRequest(MethodArgumentNotValidException e){
        log.warn(e.getMessage());
        return WebResponse.createFail(ResponseCode.IllegalRequest, e.getMessage());
    }

}
