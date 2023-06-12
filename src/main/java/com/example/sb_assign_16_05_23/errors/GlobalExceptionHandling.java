package com.example.sb_assign_16_05_23.errors;


import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mapping.MappingException;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exc, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        Map<String, String> errorMap = new HashMap<>();
        exc.getBindingResult().getFieldErrors().forEach(e -> {
            String fieldName = e.getField(); //.split("].", 2)[1]; // "list[0].studentName -> {0: list[0, 1: studentName} [1]-> studentName
            errorMap.put(fieldName, e.getDefaultMessage());
        });
        errorResponse.setMessages(errorMap);
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value=SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<?> constraintViolationExceptionHandler(SQLIntegrityConstraintViolationException exception){
        System.out.println(exception.getMessage());
        return ResponseEntity.status(500).body(exception.getMessage());
    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(Exception exc) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exc.getMessage());
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND);
        error.setMessages(errorMap);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Exception handler for handling invalid sort field
    @ExceptionHandler({PropertyReferenceException.class, MappingException.class})
    protected ResponseEntity<ErrorResponse> handleInvalidSortField(RuntimeException exc) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exc.getMessage());
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessages(errorMap);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleInvalidInputException(Exception exc) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("ErrorMessage", exc.getMessage());
        ErrorResponse error = new ErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST);
        error.setMessages(errorMap);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
