package com.smileduster.vsboard.api.exception;

import com.smileduster.vsboard.api.model.common.ResponseCode;

public class VsboardServiceException extends RuntimeException {

    private final ResponseCode code;

    public VsboardServiceException(ResponseCode code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    public VsboardServiceException(ResponseCode code, String msg) {
        super(msg);
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }
}
