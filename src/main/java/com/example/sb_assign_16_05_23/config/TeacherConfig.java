package com.example.sb_assign_16_05_23.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeacherConfig {
    @Bean
    public ModelMapper getNewModelMapper() { return new ModelMapper(); }
}
