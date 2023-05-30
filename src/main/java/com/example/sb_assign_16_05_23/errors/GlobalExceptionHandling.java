package com.example.sb_assign_16_05_23.errors;


import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandling extends ResponseEntityExceptionHandler {

    @Autowired
    private ResponseDTO responseDTO;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseDTO<?> handleBadReq(MethodArgumentNotValidException exc){
        BindingResult bindingResult = exc.getBindingResult();
        FieldError fieldError = bindingResult.getFieldErrors().get(0);
        String message = fieldError.getDefaultMessage();

//        return new ResponseEntity<>(responseDTO1, HttpStatus.BAD_REQUEST);
        ResponseDTO<String> responseDTO1 = ResponseDTO.<String>builder()
                .message(message).status(HttpStatus.BAD_REQUEST.value()).build();
//        return new ResponseEntity<>(responseDTO1, HttpStatus.BAD_REQUEST);
        return responseDTO1;
    }*/

}
