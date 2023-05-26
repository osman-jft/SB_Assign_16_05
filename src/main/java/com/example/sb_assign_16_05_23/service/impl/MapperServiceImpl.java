package com.example.sb_assign_16_05_23.service.impl;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Service
public class MapperServiceImpl {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ResponseDTO responseDTO;

    public Object Mapper(Object object, Class<?> type) {

        Object converted = modelMapper.map(object,type);
        return converted;

    }

    public ResponseDTO getResponseDTO(List<?> data, String message) {
        responseDTO.setData(data);
        responseDTO.setStatus(+HttpStatus.OK.value()+" "+ HttpStatus.OK.getReasonPhrase());
        responseDTO.setMessage(message);
        return responseDTO;
    }
}
