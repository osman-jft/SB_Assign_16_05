package com.example.sb_assign_16_05_23.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseDTO<T> {

    private List<?> data;
    private String status ;
    private String message;

    public ResponseDTO<?> getResponseDTO(List<?> data, String message) {
        ResponseDTO<T> responseDTO = new ResponseDTO<>();

        responseDTO.setData(data);
        responseDTO.setStatus(HttpStatus.OK.value() + " " + HttpStatus.OK.getReasonPhrase());
        responseDTO.setMessage(message);
        return responseDTO;
    }

    public ResponseDTO<?> getResponseDTO(List<?> data, String message, HttpStatus status){
        ResponseDTO<T> responseDTO = new ResponseDTO<>();

        responseDTO.setData(data);
        responseDTO.setStatus(status.value() + " " + status.getReasonPhrase());
        responseDTO.setMessage(message);
        return responseDTO;
    }
}

