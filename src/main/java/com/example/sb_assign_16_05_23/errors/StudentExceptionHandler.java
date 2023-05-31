package com.example.sb_assign_16_05_23.errors;

public class StudentExceptionHandler extends RuntimeException{
    public StudentExceptionHandler(String message) {
        super(message);
    }

    public StudentExceptionHandler(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentExceptionHandler(Throwable cause) {
        super(cause);
    }
}
