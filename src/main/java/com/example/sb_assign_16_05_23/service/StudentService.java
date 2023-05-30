package com.example.sb_assign_16_05_23.service;

import com.example.sb_assign_16_05_23.dto.ResponseDTO;

public interface StudentService {
    ResponseDTO<?> getAllStudents();
    List<StudentDTO> sortAccordingToRank();
    List<StudentDTO> sortAccordingTo(String sortField);
    }
