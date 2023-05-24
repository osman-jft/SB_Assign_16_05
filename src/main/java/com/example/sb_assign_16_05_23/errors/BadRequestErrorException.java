package com.example.sb_assign_16_05_23.errors;

public class BadRequestErrorException extends RuntimeException{
    public BadRequestErrorException() {
        super();
    }

    public BadRequestErrorException(String message) {
        super(message);
    }

    public BadRequestErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestErrorException(Throwable cause) {
        super(cause);
    }

}
