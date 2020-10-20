package com.smileduster.vsboard.api.exception;

public class UnknownIdException extends RuntimeException {

    public UnknownIdException(int id){
        super("Unknown id:" + id);
    }

    public UnknownIdException(String id){
        super("Unknown id:" + id);
    }

    public UnknownIdException(byte[] uuid){
        super("Unknown uuid");
    }

}
