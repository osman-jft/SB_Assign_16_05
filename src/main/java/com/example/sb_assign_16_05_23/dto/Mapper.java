package com.example.sb_assign_16_05_23.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Mapper {

    private ModelMapper modelMapper;

    public <R> R convertToType(Object source, Class<R> resultClass) {
        return modelMapper.map(source, resultClass);
    }

}
