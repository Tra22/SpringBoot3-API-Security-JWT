package com.tra22.security.exception;

public class NotFoundEntityException extends RuntimeException{
    public NotFoundEntityException(String message){
        super(message);
    }
}
