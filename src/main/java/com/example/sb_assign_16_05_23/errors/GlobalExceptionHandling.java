package com.example.sb_assign_16_05_23.errors;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleBadReq(MethodArgumentNotValidException exc){
        Map<String, Object> response = new HashMap<>();
        List<String> errorsList = new ArrayList<>();
        Map<String, String> errors = new HashMap<>();

        BindingResult bindingResult = exc.getBindingResult();
        List<FieldError> fieldError = bindingResult.getFieldErrors();

        for(FieldError e : fieldError){
            errorsList.add(e.getDefaultMessage() + " {value rejected: " + e.getRejectedValue() +  " }");
        }
        response.put("status", exc.getStatusCode());
        response.put("message" ,errorsList);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
