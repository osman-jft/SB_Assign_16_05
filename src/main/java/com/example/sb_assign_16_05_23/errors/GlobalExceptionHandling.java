package com.example.sb_assign_16_05_23.errors;


import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
@Slf4j
public class GlobalExceptionHandling {

    @Autowired
    private ResponseDTO responseDTO;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<?>> handleBadReq(MethodArgumentNotValidException exc){
        BindingResult bindingResult = exc.getBindingResult();
        FieldError fieldError = bindingResult.getFieldErrors().get(0);
        String message = fieldError.getDefaultMessage();

        ResponseDTO responseDTO1 = new ResponseDTO<>().getResponseDTO(null, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(responseDTO1, HttpStatus.BAD_REQUEST);
    }

}
