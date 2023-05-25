package com.example.sb_assign_16_05_23.config;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;
import com.example.sb_assign_16_05_23.entity.Teacher;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper getModelMapper() { return new ModelMapper(); }

   @Bean
    public ResponseDTO getResponseDTO() { return new ResponseDTO(); }

    @Bean
    public List<Teacher> getTeacherList() { return new ArrayList<>();}
}
