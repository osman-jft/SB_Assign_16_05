package com.example.sb_assign_16_05_23.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(BadRequestErrorException.class)
    public ResponseEntity<ErrorResponse> handleBadReq(BadRequestErrorException exc){
        ErrorResponse errorRes = new ErrorResponse();
        errorRes.setMessage(exc.getMessage());
        errorRes.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

}
